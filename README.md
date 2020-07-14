# TopNews

Este repositório contêm um aplicativo nativo Android programado em Kotlin e utilizando arquitetura MVVM

#### Objetivo

Construir um app que se conecta em uma API de feed de notícias, mostra os itens mais recentes do feed (pode ser um feed de mais recentes ou de "top" / mais populares), permite abrir cada artigo completo no navegador e permite ocultar os itens já lidos.

##### Descrição

Utilizei a API de Top Stories do The New York Times, ao carregar o aplicativo, ou ao clicar na opção "Atualizar" do menu, o feed vai ser carregado e o app será mostrado as 20 primeiras notícias
Exibindo o título, autor e imagem de cada notícia.
Ao clicar em um item, abrir a URL do item no navegador padrão
Cada item terá uma opção "Lido" que o marca como lido e o oculta da lista
Um item lido não volta mais à lista
Uma opção "Desmarcar lidos" para restaurar os itens ocultos
Ao ocultar um item da lista, carregar/mostrar um item a mais do feed para manter o número de itens mostrado originalmente (enquanto houver mais itens no feed, é claro) – por exemplo, se 2 itens tiverem sido ocultos, mostrar os itens 21 e 22
Salvar os itens lidos de forma persistente – ou seja, ao "matar" o aplicativo e reabri-lo, os itens anteriormente lidos devem continuar ocultos

#### O app contém os seguintes pacotes:
1. **data**: Contém todos os dados acessando e manipulando componentes.
2. **presentation**: Todas as Views e ModelViews correspondentes.
3. **utils**: Classes de utilidade em geral.

#### As classes foram projetadas de forma que possam ser herdadas e sua reutilização seja maximizada.

### Bibliotecas utilizadas:
1. **Picasso**: https://github.com/square/picasso
2. **Retrofit**: https://square.github.io/retrofit/
3. **Room**: developer.android.com/topic/libraries/architecture/room
4. **Moshi**: https://github.com/square/moshi
5. **Mockito**: https://developer.android.com/training/testing/unit-testing/local-unit-tests