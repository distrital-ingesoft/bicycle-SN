#!/bin/bash

CONTAINER_NAME=gitbook

# No existe la imagen de contenedor ?
if [ ! "$(docker images -q $CONTAINER_NAME)" ]; then

    # Crea la imagen usando docker compose
    docker compose build $CONTAINER_NAME
fi

# No se está ejecutando el contenedor ?
if [ ! "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then

    # Terminó con errores ?
    if [ "$(docker ps -aq -f status=exited -f name=$CONTAINER_NAME)" ]; then
        # cleanup
        docker rm $CONTAINER_NAME
    fi

    # Ejecuta el contenedor
    docker compose up -d $CONTAINER_NAME
fi

# Ejecuta el comando en el contenedor
docker exec -it $CONTAINER_NAME honkit "$@"