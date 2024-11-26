build-image:
        @ docker build -f Dockerfile -t prueba:1 .
volume:
        @ docker volume create pg_tendencia_instituto_data
network:
        @ docker network create --driver overlay --scope swarm desarrollo_net || true
deploy:
        @ docker stack deploy --with-registry-auth -c stack.yml prueba
rm:
        @ docker stack rm prueba