﻿# Selenium
___
Projeto disponibilizado pela Alura para estudo com testes automatizados com Selenium
___

## Recaptulando

### Controlando o navegador

1. Fiz a intalação da dependencia do Selenium.


        <dependency>
   		<groupId>org.seleniumhq.selenium</groupId>
   		<artifactId>selenium-chrome-driver</artifactId>
   		<scope>test</scope>
   	    </dependency>

2. Identifiquei a minha versão do chrome.
3. Baixei driver do chromedriver correspondente a minha versão.
4. Criei uma classe de teste a partir da classe LeilaoAplication e fiz meu primeiro teste com o Selenium


        @Test
        void main() {
            System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver-win64\\chromedriver.exe");
            WebDriver browser = new ChromeDriver();
            browser.navigate().to("http://localhost:8080/leiloes");
            browser.quit();
        }


### Testando o login

1. Criei um pacote login e criei uma classe LoginTest
2. Teste de login com dados validos. Pequenas alterações foram necessárias em login.html e base.html


        @Test
        void efetuarLoginComDadosValidos() {
            browser.findElement(By.id("username")).sendKeys("fulano");  // procura um elemento pelo id e passa um texto como chave  
            browser.findElement(By.id("password")).sendKeys("pass");
            browser.findElement(By.id("login-form")).submit();

            Assert.assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/login"));  // Garante que o usuário *saiu* da pagina de login depois da autenticação
            Assert.assertEquals("fulano Sair", browser.findElement(By.id("usuario-logado")).getText()); // Garante que o usuario logado é o mesmo que fez a autenticação

            browser.quit(); // fecha o navegador
        }

3. Teste de login com dados invalidos.

              @Test
              void loginComDadosInvalidos() {
                 browser.findElement(By.id("username")).sendKeys("Usuario Invalido"); // procura um elemento pelo id e passa um texto como chave
                 browser.findElement(By.id("password")).sendKeys("1234"); // procura um elemento pelo id e passa um texto como chave
                 browser.findElement(By.id("login-form")).submit(); // procura um elemento pelo id e por ser um form apenas de um submit irá acionar o butão

                 Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));   // Garante que o usuário permanece na tela de login
                 Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));  // Garante que o usuario recebeu uma mensagem de usuário e senha inválidos.
              }
