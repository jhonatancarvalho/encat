# Encat
[![Build Status](https://travis-ci.org/jhonatancarvalho/encat.svg?branch=master)](https://travis-ci.org/jhonatancarvalho/encat)
[![codecov](https://codecov.io/gh/jhonatancarvalho/encat/branch/master/graph/badge.svg)](https://codecov.io/gh/jhonatancarvalho/encat)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=br.com.jhonatan%3Aencat&metric=alert_status)](https://sonarcloud.io/dashboard?id=br.com.jhonatan%3Aencat)

Webservice para criação de enquetes em Java.

Disponível em: https://encat-api.herokuapp.com/


## Endpoints


__[GET]__ https://encat-api.herokuapp.com/enquetes/{id} - Retorna uma enquete pelo id.


__[POST]__ https://encat-api.herokuapp.com/enquetes - Cadastra uma nova enquete, exemplo de body:
```sh
{
  "pergunta": "Você gosta de gatos?",
  "opcoes": ["Sim", "Não"]
}
```


__[GET]__ https://encat-api.herokuapp.com/enquetes/page - Retorna as enquetes paginadas, com possibilidate de inserção dos parâmetros page, size, direction e orderBy.


__[PUT]__ https://encat-api.herokuapp.com/enquetes/votar/{opcaoId} - Efetua o voto em uma opção da enquete.

## License
MIT
