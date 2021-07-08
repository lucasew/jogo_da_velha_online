# Jogo da Velha Online

Implementação do jogo da velha usando Java e o framework Quarkus.

O boilerplate e os scripts são os mesmos do framework original então os comandos são os mesmos.

Este projeto funciona com banco postgresql e está sendo hospedado em https://jogo-da-velha-online-lpoo.herokuapp.com/

**Configuração necessária -- Variáveis de ambiente**
- `DATABASE_HOST`: Host do banco de dados postgres
- `DATABASE_NAME`: Nome do banco de dados no host
- `DATABASE_PASSWD`: Senha do banco de dados
- `DATABASE_PORT`: Porta do banco de dados, geralmente 5432
- `DATABASE_USER`: Usuário do banco de dados

Ao executar o comando para iniciar a aplicação em modo dev o Quarkus vai tentar subir um container do postgres via Docker e vai configurar tudo azeitadinho.

Se você não tem Docker para o ambiente dev não sei o que acontece, no pior dos casos a aplicação só dá pau e nem sobe.

Fiz esse projeto no Intellij, como é um projeto Maven pode pegar em basicamente qualquer IDE Java mas eu não garanto. Se alguém testar reporta nas issues por favor hehehe.
