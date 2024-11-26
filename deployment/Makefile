build-image:
        @ docker build -f Dockerfile -t moldycarrots:1 .

deploy:
        @ docker stack deploy --with-registry-auth -c stack.yml moldycarrots_backend

rm:
        @ docker stack rm moldycarrots_backend

crear-imagen:
        @ docker build -f Dockerfile -t moldycarrots:1 .

crear-red:
        @ docker network create --driver overlay --scope swarm moldycarrots_net || true

borrar-red:
        @ docker network rm moldycarrots_net

crear-volumen:
        @ docker volume create pg_moldycarrots_data

borrar-volumen:
        @ docker volume rm pg_moldycarrots_data