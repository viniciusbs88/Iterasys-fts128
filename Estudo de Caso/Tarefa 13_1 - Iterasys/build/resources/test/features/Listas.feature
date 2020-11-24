#language: pt
#encoding: utf8
  Funcionalidade: Listas
    Criar e manter listas

  Contexto:
    Dado que eu acesso o site Microsoft To Do
    Quando clico no icone do usuario
    Entao o site realiza o login e exibe a pagina do To Do

  Esquema do Cenario: Criar uma lista
    Quando clico em Nova lista
    E preencho <novaLista> e pressiono Enter
    Entao exibe a lista <novaLista> vazia
    Exemplos:
      | novaLista             |
      | "Lista de Compras"    |
      | "Minha Outra Lista"   |
      | "Lista Ceia de Natal" |

  Esquema do Cenario: Alterar uma lista
    Quando clico na lista <listaOriginal>
    Entao exibe a <listaOriginal> vazia
    Quando realizo duplo clique no nome da lista <listaOriginal>
    E altero o nome da lista para <listaAlterada> e pressiono Enter
    Entao exibe a lista com o nome alterado para <listaAlterada>
    Exemplos:
    | listaOriginal         | listaAlterada                  |
    | "Lista de Compras"    | "Lista de Compras Alterada"    |
    | "Minha Outra Lista"   | "Minha Outra Lista Alterada"   |
    | "Lista Ceia de Natal" | "Lista Ceia de Natal Alterada" |

  Esquema do Cenario: Alterar uma lista pelo menu reticencias
    Quando clico na lista <listaOriginal>
    Entao exibe a <listaOriginal> vazia
    Quando clico no icone reticencias ao lado do nome <listaOriginal>
    Entao exibe o menu Opcoes da lista
    Quando seleciono a opcao Renomear lista
    E altero o nome da lista para <listaAlterada> e pressiono Enter
    Entao exibe a lista com o nome alterado para <listaAlterada>
    Exemplos:
      | listaOriginal         | listaAlterada                  |
      | "Lista de Compras"    | "Lista de Compras Alterada"    |
      | "Minha Outra Lista"   | "Minha Outra Lista Alterada"   |
      | "Lista Ceia de Natal" | "Lista Ceia de Natal Alterada" |

  Esquema do Cenario: Consultar Lista
    Quando clico na lista <nomeLista>
    Entao exibe a lista <nomeLista> vazia
    Exemplos:
      | nomeLista             |
      | "Lista de Compras"    |
      | "Minha Outra Lista"   |
      | "Lista Ceia de Natal" |

  Esquema do Cenario: Excluir Lista
    Quando clico na lista <nomeLista> com o botao direito
    Entao exibe a lista <nomeLista> vazia e o menu com a opcao Excluir lista
    Quando clico na opcao Excluir lista
    Entao exibe a mensagem: <nomeLista> sera definitivamente excluida. Voce nao podera desfazer esta acao.
    Quando clico na opcao Excluir Lista
    Entao a lista <nomeLista> e excluida e desaparece do menu lateral
  Exemplos:
    | nomeLista             |
    | "Lista de Compras"    |
    | "Minha Outra Lista"   |
    | "Lista Ceia de Natal" |

  Esquema do Cenario: Excluir Lista pelo menu reticencias
    Quando clico na lista <nomeLista>
    Entao exibe a <nomeLista> vazia
    Quando clico no icone reticencias ao lado do nome <nomeLista>
    Entao exibe o menu Opcoes da lista
    Quando clico na opcao Excluir lista
    Entao exibe a mensagem: <nomeLista> sera definitivamente excluida. Voce nao podera desfazer esta acao.
    Quando clico na opcao Excluir Lista
    Entao a lista <nomeLista> e excluida e desaparece do menu lateral
    Exemplos:
      | nomeLista             |
      | "Lista de Compras"    |
      | "Minha Outra Lista"   |
      | "Lista Ceia de Natal" |

  Esquema do Cenario: Compartilhar lista por email
    Quando clico na lista <nomeLista>
    Entao exibe a <nomeLista> vazia
    Quando clico no icone Compartilhar
    Entao exibe a caixa Compartilhar lista
    Quando clico na opcao Criar link do convite
    Entao exibe o link de compartilhamento da lista <nomeLista> e as opcoes Convidar por email, Copiar link e Gerenciar acesso
    Quando clico na opcao Convidar por email
    Entao exibe o aplicativo de email com uma mensagem contendo o link de compartilhamento da lista <nomeLista>
    Exemplos:
      | nomeLista             |
      | "Lista de Compras"    |
      | "Minha Outra Lista"   |
      | "Lista Ceia de Natal" |

    Esquema do Cenario: Compartilhar lista por link
    Quando clico na lista <nomeLista>
    Entao exibe a <nomeLista> vazia
    Quando clico no icone Compartilhar
    Entao exibe a caixa Compartilhar lista
    Quando clico na opcao Criar link do convite
    Entao exibe o link de compartilhamento da lista <nomeLista> e as opcoes Convidar por email, Copiar link e Gerenciar acesso
    Quando clico na opcao Copiar link
    Entao exibe a mensagem: Copiado para a area de transferência
    Exemplos:
      | nomeLista             |
      | "Lista de Compras"    |
      | "Minha Outra Lista"   |
      | "Lista Ceia de Natal" |

    Esquema do Cenario: Parar de compartilhar lista
    Quando clico na lista <nomeLista>
    Entao exibe a <nomeLista> vazia
    Quando clico no icone Compartilhar
    Entao exibe a caixa Compartilhar lista
    Quando clico na opcao Gerenciar acesso
    Entao exibe a caixa Gerenciar acesso
    Quando clico na opcao Parar de compartilhar
    Entao exibe a mensagem: Deseja interromper o compartilhamento?
    Quando clico na opcao Parar de compartilhar
    Entao a lista <nomeLista> deixa de ser compartilhada
    Exemplos:
      | nomeLista             |
      | "Lista de Compras"    |
      | "Minha Outra Lista"   |
      | "Lista Ceia de Natal" |
