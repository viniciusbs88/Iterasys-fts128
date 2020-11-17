#language: pt
#encoding: utf8
  Funcionalidade: Lista
    Criar e manter itens em uma lista
  Esquema do Cenario: Incluir itens na lista
    Dado que estou na <nomeLista>
    Quando digito <item> e pressiono Enter
    Entao exibe o <item> na <nomelista>
    Exemplos:
      | nomeLista             | item              |
      | "Lista de Compras"    | "macarrão"        |
      | "Minha Outra Lista"   | "molho de tomate" |
      | "Lista Ceia de Natal" | "uvas passas"     |

  Esquema do Cenario: Alterar itens na lista
    Dado que estou na <nomeLista>
    Quando seleciono o <item>
    Entao exibe o menu lateral direito com mais informacoes do item <item>
    Quando realizo duplo clique no nome do item <item>
    Entao exibe nome do item <item> selecionado
    Quando digito o novo nome <itemAlterado> e pressiono Enter
    Entao o nome do item e alterado para <itemAlterado>

    Exemplos:
      | nomeLista             | item              | itemAlterado |
      | "Lista de Compras"    | "macarrão"        | "espaguete"  |
      | "Minha Outra Lista"   | "molho de tomate" | "tomate"     |
      | "Lista Ceia de Natal" | "uvas passas"     | "nozes"      |

  Esquema do Cenario: Consultar itens existentes na lista
    Dado que eu acesso o site Microsoft To Do
    Quando clico no icone do usuario
    Entao o site realiza o login e exibe a pagina do To Do
    Quando clico na entrada Pesquisar e digito <item>
    Entao exibe uma lista contendo <quantidade> de ocorrencias do item <item> nas listas existentes
    Exemplos:
    | item        | quantidade  |
    | "espaguete" | 1           |
    | "tomate"    | 1           |
    | "nozes"     | 1           |

  Cenario: Consultar item inexistente nas listas
    Dado que eu acesso o site Microsoft To Do
    Quando clico no icone do usuario
    Entao o site realiza o login e exibe a pagina do To Do
    Quando clico na entrada Pesquisar e digito Item Inexistente
    Entao exibe a mensagem: Procuramos em toda parte, mas nao encontramos o que voce esta procurando.

  Esquema do Cenario: Excluir itens da lista pelo menu
    Dado que estou na <nomeLista>
    Quando pressiono o botao direito sobre o <item>
    Entao exibe o menu suspenso
    Quando seleciono a opcao Excluir tarefa
    Entao exibe a caixa suspensa com a mensagem: <item>sera definitivamente excluida. Voce nao podera desfazer esta acao.
    Quando seleciono a opcao Excluir tarefa
    Entao exibe a lista <nomeLista> sem o item <item>
    Exemplos:
      | nomeLista             | item              |
      | "Lista de Compras"    | "macarrão"        |
      | "Minha Outra Lista"   | "molho de tomate" |
      | "Lista Ceia de Natal" | "uvas passas"     |

  Esquema do Cenario: Excluir itens da lista pelos detalhes do item
    Dado que estou na <nomeLista>
    Quando seleciono o <item>
    Entao exibe o menu lateral direito com mais informacoes do item <item>
    Quando seleciono a opcao Excluir tarefa
    Entao exibe a caixa suspensa com a mensagem: <item>sera definitivamente excluida. Voce nao podera desfazer esta acao.
    Quando seleciono a opcao Excluir tarefa
    Entao exibe a lista <nomeLista> sem o item <item>
    Exemplos:
      | nomeLista             | item              |
      | "Lista de Compras"    | "macarrão"        |
      | "Minha Outra Lista"   | "molho de tomate" |
      | "Lista Ceia de Natal" | "uvas passas"     |
