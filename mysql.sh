#!/bin/sh

# ejecute `mysql -p` dentro del contenedor `cyship-db`
docker exec -it cyship-db mysql -u cyship -p