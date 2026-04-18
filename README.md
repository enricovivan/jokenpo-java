# Jokenpo API

Uma API REST completa para jogar o clássico jogo **Jokenpo** (Pedra, Papel e Tesoura) contra o computador. Construída com Java e Spring Boot, esta API não só permite que você jogue, mas também mantém um registro de todos os jogadores e do histórico de todas as partidas.

## Índice
- [O que essa API faz?](#o-que-essa-api-faz)
- [Como Jogar](#como-jogar)
- [Endpoints Disponíveis](#endpoints-disponíveis)
- [Rate Limiter (Limite de Requisições)](#rate-limiter-limite-de-requisições)
- [Documentação Swagger / OpenAPI](#documentação-swagger--openapi)

## O que essa API faz?

A **Jokenpo API** simula partidas de Pedra, Papel e Tesoura. Ela permite que um usuário envie uma jogada (Pedra, Papel ou Tesoura) e informa o resultado da partida processando aleatoriamente a jogada do computador.

Principais funcionalidades:
- **Jogar Jokenpo**: Recebe a jogada do jogador e compara com a do computador para determinar se o jogador ganhou, perdeu ou se houve empate.
- **Gerenciamento de Jogadores**: Grava o nome dos jogadores, permitindo consultar a lista de jogadores já cadastrados e buscar jogadores específicos por ID.
- **Histórico de Partidas**: Todas as partidas são salvas no banco de dados. É possível visualizar o histórico de todas as partidas jogadas, ou buscar de forma filtrada por um ID de jogador, nome do jogador ou ID da partida em si.
- **Segurança Antispam (Rate Limiting)**: Conta com um sistema de limite de requisições por IP para evitar abusos no consumo da API.

## Como Jogar

Para jogar, você deve fazer uma requisição do tipo `POST` para o endpoint `/game/play`.

### Exemplo de Requisição (Jogar)

**Endpoint:** `POST /game/play`

**Corpo (Body) no formato JSON:**
```json
{
  "option": "PEDRA",
  "player": {
    "name": "João da Silva"
  }
}
```
*Observação: As opções válidas para o campo `option` são `PEDRA`, `PAPEL` ou `TESOURA`.*

### Exemplo de Resposta

A API processará sua jogada contra uma escolhida aleatoriamente pelo computador e retornará o resultado:

```json
{
  "message": "Você ganhou!",
  "result": "VITORIA",
  "playerOption": "PEDRA",
  "computerOption": "TESOURA"
}
```
*Os resultados possíveis em `result` são `VITORIA`, `DERROTA` ou `EMPATE`.*

## Endpoints Disponíveis

A API expõe diversas rotas (endpoints) para interação:

### Jogo
- `POST /game/play`: Inicia uma partida enviando a jogada do usuário e retorna o resultado.

### Jogadores
- `GET /players/all`: Retorna uma lista de todos os jogadores registrados.
- `GET /players/{id}`: Busca e retorna as informações de um jogador específico pelo seu UUID (ID).

### Histórico
- `GET /history/all`: Retorna o histórico de todas as partidas registradas.
- `GET /history/{id}`: Busca uma partida específica pelo seu UUID (ID).
- `GET /history/player/name/{playerName}`: Retorna todo o histórico de partidas de um jogador com base em seu nome.
- `GET /history/player/id/{playerId}`: Retorna todo o histórico de partidas associadas ao UUID de um jogador específico.

## Rate Limiter (Limite de Requisições)

Para garantir a estabilidade do sistema e evitar ataques de negação de serviço (DoS) ou flood, a API conta com um **Limitador de Requisições (Rate Limiter)** implementado via `Bucket4j`.

Este rate limiter funciona da seguinte maneira:
- **Identificação por IP**: Cada endereço IP que acessa a API tem seu "balde" (bucket) individual de limites.
- **Capacidade Inicial**: Cada IP pode fazer até **150 requisições seguidas** (rajada).
- **Recarga (Refill)**: O limite não é estático. A cada fôlego, o limite ganha mais **75 requisições por minuto**. 
- **O que acontece ao exceder?** Caso o endereço IP dispare mais requisições do que o seu limite permite, a API bloqueará as novas requisições e retornará o status HTTP **429 (Too Many Requests)** com a mensagem: `"Vai com calma ai campeao"`. As requisições voltarão a ser aceitas conforme a taxa de renovação preencha o seu limite novamente.

## Documentação Swagger / OpenAPI

Para visualizar toda a documentação da API de forma interativa, realizar testes direto do seu navegador e ver os esquemas de objetos retornados detalhadamente, acesse a interface do Swagger UI da aplicação.

**URL da Documentação:**
[http://localhost:8080/docs](http://localhost:8080/docs) 

*(A porta 8080 é a porta padrão assumida pelo Spring Boot localmente. Caso esteja em produção/ambiente de deploy, acesse `seudominio.com/docs`)*
