# CQRS-with-kafka

# Below API to test producer and consumer API

## Create account
```bash
curl -X POST "http://localhost:8080/accounts/create?accountId=acc123&owner=Alice"

```

## Deposit money

```bash

curl -X POST "http://localhost:8080/accounts/deposit?accountId=acc123&amount=100"

```

## Withdraw money

```bash

curl -X POST "http://localhost:8080/accounts/withdraw?accountId=acc123&amount=30"

```

## Query balance
```bash

curl "http://localhost:8081/query/balance?accountId=acc123"

```