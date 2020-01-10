```
docker run --name some-postgres -p 5410:5432 -v postgres-data:/var/lib/postgresql/data  -d postgres-schema 

docker build -t postgres-schema -f Dockerfile.postgres . 
```

