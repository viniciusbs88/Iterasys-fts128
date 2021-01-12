#language: pt
#encoding utf8

  Funcionalidade: Consultar Produto
    Como um cliente eventual, gostaria de consultar a disponibilidade e preço de alguns produtos que tenho interesse em adquirir.

  Esquema do Cenario: Consultar Produto Fixo
    Dado que acesso o site da Cobasi
    Quando procuro por <produto>
    Entao exibe uma lista de produtos relacionados a <produto>
    Quando seleciono o primeiro produto da lista
    Entao verifico a marca como <marca> com preco normal de <precoNormal> e <precoAssinantes> para assinantes
  Exemplos:
    | produto         | marca         | precoNormal | precoAssinantes |
    | "Ração"         |"Golden"       |"R$ 18,90"   |"R$ 17,01"       |
    | "Ração Hamster" |"Criação Forte"|"R$ 14,90"   |"R$ 13,41"       |


    Esquema do Cenario: Login Usuario
    Dado que acesso o site da Cobasi
    Quando seleciono a opcao ENTRAR
    Entao exibe a pagina de login de usuario
    Quando seleciono a opcao de RECEBER CHAVE POR EMAIL
    Entao exibe a pagina de solicitacao de email
    Quando preencho o campo <email> no formulario de recebimento de chave
    E pressiono o botao CONFIRMAR
    Entao exibe a pagina de validacao da chave
    Quando preencho o campo chave com o valor recebido no email <email>
    E pressiono o botao ENTRAR
    Entao verifico que o usuario <usuario> realizou login com sucesso

    Exemplos:
      |usuario    |email                              |senha       |
      |"Caroline" |"fts128_vinicius_cobasi1@maildrop.cc"|"dqFBFLgFnK1"|
      |"Arthur"   |"fts128_vinicius_cobasi2@maildrop.cc"|"FuSfQkCy7u"|
      |"Vinicius" |"fts128_vinicius_cobasi3@maildrop.cc"|"K01j6r4XQ8"|