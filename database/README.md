```
docker run --name some-postgres -p 5410:5432 -v postgres-data:/var/lib/postgresql/data -d postgres-python 

docker build -t postgres-python -f Dockerfile.postgres . 
```

