```
docker run --name some-postgres -v postgres-data:/var/lib/postgresql/data -d postgres-python 

docker build -t postgres-python -f Dockerfile.postgres . 
```

