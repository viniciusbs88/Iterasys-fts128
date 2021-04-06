#language: pt
#encoding utf8

  Funcionalidade: Consultar Produto
    Como um cliente eventual, gostaria de consultar a disponibilidade e preço de alguns produtos que tenho interesse em adquirir.

  Cenario: Consultar Produto Fixo
    Dado que acesso o site da Petz
    Quando procuro por "Ração"
    Entao exibe uma lista de produtos relacionados a "Ração"
    Quando seleciono o primeiro produto da lista
    Entao verifico a marca como "Royal Canin" com preco normal de "R$ 232,69" e "R$ 209,42" para assinantes

  Esquema do Cenario: Cadastrar Usuario
    Dado que acesso o site da Petz
    Quando seleciono a opcao CRIAR CONTA
    Entao exibe a pagina de cadastro de novo usuario
    Quando preencho os campos <nome>, <email>, <ddd>, <celular>, <dddTel>, <telefone>, <sexo>, <nascimento>, <cpf>, <senha> no cadastro
    E pressiono o botao CRIAR CONTA
    Entao verifico que o usuario foi criado com sucesso

    Exemplos:
    |nome                       |cpf             |nascimento  |sexo       |email                              |senha       |dddTel|telefone   |ddd |celular     |
    |"Caroline Regina Viana"    |"845.405.046-90"|"16/02/1952"|"Feminino" |"fts128_vinicius_petz1@maildrop.cc"|"dqFBFLgFnK"|"61"  |"2937-5468"|"61"|"99398-1386"|
    |"Arthur Victor Lucas Costa"|"696.677.054-04"|"24/06/1974"|"Masculino"|"fts128_vinicius_petz2@maildrop.cc"|"FuSfQkCy7u"|"88"  |"2936-8514"|"88"|"98170-9377"|
    |"Vinicius Geraldo Baptista"|"373.183.450-28"|"25/08/1952"|"Masculino"|"fts128_vinicius_petz3@maildrop.cc"|"K01j6r4XQ8"|"34"  |"3549-8239"|"34"|"98855-6840"|


  Esquema do Cenario: Login Usuario
    Dado que acesso o site da Petz
    Quando seleciono a opcao ENTRAR
    Entao exibe a pagina de login de usuario
    Quando preencho os campos <email> e <senha> no formulario de login
    E pressiono o botao ENTRAR
    Entao verifico que o usuario <usuario> realizou login com sucesso

    Exemplos:
      |usuario    |email                              |senha       |
      |"Caroline" |"fts128_vinicius_petz1@maildrop.cc"|"dqFBFLgFnK"|
      |"Arthur"   |"fts128_vinicius_petz2@maildrop.cc"|"FuSfQkCy7u"|
      |"Vinicius" |"fts128_vinicius_petz3@maildrop.cc"|"K01j6r4XQ8"|