#Trabalho de Laboratório de Programação III - 2017.3

#### Nome: Lucas Diniz da Costa Matricula: 201465524A   Curso: Ciências Exatas

**Texto Explicativo:**<p align="justify"> O sistema tem como objetivo gerenciar os pedidos em uma lanchonete durante um dia de trabalho. Cada pedido tem o seu número correspondente a posição na lista de pedidos, deve ser passado o número da mesa do cliente. Possui uma interface para cadastrar produtos no cardápio, cadastrar pedidos, pedidos abertos e controle de caixa e uma opção de cadastro do numero de mesas no menu principal. Ainda é permitido ao usuário verificar em cada pedido os produtos, o valor total e a hora de entrada e saída do pedido. O sistema será utilizado pelos funcionários que anotarão os pedidos realizados no horário de serviço, de modo que serão armazenados os produtos, número do pedido, número da mesa e data e hora de entrada e saída do pedido. Todos os dados do programa serão armazenados em arquivos de texto como banco de dados.</p>

**Modelo de dados:**

<a href="https://ibb.co/kX83Yw"><img src="https://image.ibb.co/iMygLb/Main.png" alt="Main" border="0"></a>

**Classes utilizadas:**

- Interfaces:
	- CadastrarPedido;
	- CadastrarProduto;
	- ControleCaixa;
	- MenuPrincipal;
	- PedidoAberto;
- Listas personalizadas:
	- CaixaDiarioListModel;
	- PedidoListModel;
	- ProdutoListModel;
- Dados:
	- Calendario;
	- CaixaDiario;
	- Cardapio;
	- ControleArquivos;
	- FluxoCaixa;
	- Pedido;
	- Produto;
- Principal:
	- Main;

**Campos necessários para composição das interfaces:**

- <p align="justify">**Menu Principal:** Título, botões para acessar as interfaces de Pedidos Abertos, Cadastrar Produtos, Cadastrar Pedidos e Controle Caixa. Além de possuir os campos de controle do número de mesas com a opção de adicionar ou remover uma mesa;</p>
- <p align="justify">**Pedidos Abertos:** Possui duas listas, uma responsável por exibir os pedidos abertos cadastrados no sistema e outra responsável por exibir os produtos contidos no pedido selecionado na primeira lista, além de apresentar os títulos para a identificação da lista correspondente, possui botões para finalizar ou editar o pedido, campos que exibem a data/hora de entrada, número da mesa e o valor total do pedido;</p>
- <p align="justify">**Cadastrar Pedido:** Possui duas listas, uma responsável por exibir os produtos contidos no cardápio e outra responsável por exibir os produtos contidos no pedido, além de apresentar os títulos para a identificação da lista correspondente, ainda possui um campo de texto exibindo o número do pedido e botões para adicionar o produto selecionado no cardápio ao pedido, remover o produto selecionado no pedido e um botão para concluir o pedido. Contém também um campo responsável por captar o número da mesa em que o pedido é feito;</p>
- <p align="justify">**Cadastrar Produto:** campos de texto responsáveis por capturar o nome e preço do produto, possui uma lista responsável por exibir os produtos cadastrados no cardápio além de botões para adicionar, modificar, remover o produto no cardápio.</p>
- <p align="justify">**Controle Caixa:** Possui três listas, uma sendo responsável por exibir os caixas diários do sistema, outra responsável por exibir os pedidos no determinado dia e outra que exibe os produtos pedidos no pedido determinado. Ainda possui campos de pedido, mesa, valor recebido, entrada e saída mostrando os dados do pedido selecionado e outro campo de valor total recebido no dia que tem como função facilitar o controle de caixa exibindo a quantia de dinheiro recebida no dia, possui um botão remover que possibilita a remoção de um caixaDiario ou de um pedido selecionado;</p>

**Funcionamento da interface:**

- <p align="justify">É necessário uma lista para receber os pedidos abertos no sistema, uma variavel cardapio que possui uma lista que recebe os produtos cadastrados no sistema, um caixaDiario que tem como objetivo estocar os pedidos feitos no dia e o FluxoCaixa que recebe todos os caixaDiario cadastrados no sistema, serve para controle de caixa.</p>
- <p align="justify">Através da interface de **"Pedidos Abertos"** é possível a edição de um pedido que é feita a partir da mesma interface do **"Cadastrar Pedido"**, com o adendo de que a invés de criar um pedido novo, ela irá receber um pedido com restrição para não remover os produtos feitos no pedido anterior e os campos de mesa e pedido serem preenchidos com os dados dele.</p>
- <p align="justify">Na interface de **"Cadastrar Produto"**, antes de adicionar o produto a lista de produtos do cardápio, o sistema deve verificar se o produto com o nome digitado não está cadastrado, se estiver deve informar ao usuário sobre o produto já estar presente. Além disso, o campo responsável por capturar o preço do produto deve possuir a formatação de dinheiro da seguinte forma ".00", e se o usuário preencher o campo de forma que não corresponda ao formato estabelecido, o sistema deve informar sobre o possível erro.</p>
- <p align="justify">Na interface de **"Cadastrar Produto"**, antes de remover o produto do cardápio, o usuário deve selecioná-lo na lista.</p>
- <p align="justify">Na interface de **"Cadastrar Pedido"**, deve ser selecionado um produto da lista de produtos para poder adicioná-lo ao pedido, para remover um produto do pedido deve ser selecionado um produto presente na lista de pedido e pressionado o botão para executar a ação;</p>
- <p align="justify">Na interface de **"Cadastrar Pedido"**, ao ser inicializada será capturada a data e hora de entrada e armazenada em uma variável do tipo "Calendário";</p>
- <p align="justify">Na interface de **"Cadastrar Pedido"**, ao concluir pedido será capturada a data e hora de saída e armazenada em uma variável do tipo **"Calendário"** dentro do pedido. Isso tem o objetivo de registrar os dados de entrada de quando o pedido foi iniciado e facilitar o encerramento dele, além de permitir o controle de caixa posterior;</p>
- <p align="justify">Na interface de **"Cadastrar Pedido"**, para concluir o pedido é necessário que o campo de número da mesa seja preenchido(desde que venha a partir do menu principal), caso não seja, o sistema deve atentar o usuário para que o preencha. Ainda, o número da mesa deve estar de acordo com o número máximo de mesas, ou seja, maior que ele não será válido e menor que 1 também não;</p>
- <p align="justify">Na interface de **"Pedidos Abertos"**, deve ser selecionado um pedido para que possa ser exibida a lista de produtos. No nome do pedido é apresentado seu "Número de pedido", os outros dados de entrada, valor total e mesa serão exibidos em outros campos na interface. Ainda é permitido que possa ser adicionados novos produtos ao pedido através do botão **"editar"**, de modo que guiará o usuário a interface de **"Cadastrar Pedido"** de forma diferenciada, voltada para receber um pedido já feito;</p>
- <p align="justify">Na interface de **"Controle Caixa"**, deve ser selecionado um "dia" para ser exibido a lista de  pedidos e o "valor total recebido". Ao selecionar um pedido, será exibido a lista com os produtos e os dados particulares daquele pedido;</p>

**Protocolo de persistência de dados utilizado:**

- <p align="justify">O protocolo utilizado foi a criação de arquivos de texto para armazenar os dados. O problema presente em trabalhar com arquivos de texto seria de que toda vez que um dado for adicionado ou removido, o arquivo todo deve ser escrito de novo. Isso é bem ruim pois atrasa bastante no progresso do sistema e pode ocorrer problemas bem piores quando a massa de dados for bem grande. Por isso a melhor persistência que há seria em utilizar o banco de dados, pois cada mudança ocorrida não é necessário atualizar todo o banco de dados, sendo que no arquivo de texto é preciso;</p>

**Pontos de dificuldade de implementação:**


1. <p align="justify">Ao adicionar um produto ao pedido, o campo de quantidade estava incrementando tanto no pedido quanto no cardápio e isso foi um problema. O problema se encontrava no método "Adicionar Produto" dentro da classe "Pedido", ele estava adicionando o produto na lista corretamente mas ao tentar adicionar o produto com a função "produtos.add(produto)" ele estava adicionando o mesmo produto do cardápio na lista de produtos do pedido com o mesmo endereço de memória, ou seja, qualquer alteração feita nele acarretaria em uma mudança no produto do cardápio. Mesmo tentando alternativas como "*Produto prod = produto*" ele continuava referindo ao mesmo produto,  a solução encontrada foi em instanciar um novo produto e passando como parâmetros o "*nome*" e "preço" desse produto do cardápio, do seguinte modo "Produto aux = new Produto(produto.getNome(), produto.getPreco());". Assim, esse produto é criado em outro endereço de memória podendo ser inserido e alterado sem interferir no produto do cardápio.</p>
2. <p align="justify">Desenvolver a persistência de dados em arquivos de texto de modo que o usuário possa ler e entender sem a necessidade do programa foi complicada, pois dividir strings e concatenar em alguns casos causavam erros de posições inválidas como o  erro "*java.lang.StringIndexOutOfBoundsException*". Apesar de que seria possível criar um arquivo de leitura usando "split" de strings e o arquivo de texto seria mais facilmente lido pelo programa mas causaria uma dificuldade ao usuário.</p>
	
**Pontos que podem ser melhorados:**

- <p align="justify">Pode-se fazer uma associação a um banco de dados de modo a seguir a ideia da interface "Controle Caixa", que armazenara os dias, pedidos, produtos, valor recebido no pedido, valor total recebido no dia, e ainda armazenar os produtos cadastrados no cardápio. Pois apesar do arquivo de texto auxiliar bastante na armazenagem dos dados, esse método é muito inferior ao banco de dados pois a cada adição/remoção deve ser adicionado todos os dados novamente(com as modificações) enquanto que no banco de dados a adição/remoção é muito fácil e ágil pois só afeta aquele dado em específico sem ter que mexer na base toda;</p>