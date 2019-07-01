Feature: Mensagem


Scenario: enviarMensagem
Given usuario navega para site Verity Group
When usuario preenche Empresa "Empresa Erick" Nome "Erick Valentin" Email "ovalen507@gmail.com" Telefone "81998298730" Assunto "teste" Mensagem "teste de Mensagem"
Then texto "Sua mensagem foi enviado com sucesso!" exibida