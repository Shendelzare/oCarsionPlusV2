FROM postgres:latest

EXPOSE 5432

ENV POSTGRES_PASSWORD ocarsionplus
ENV POSTGRES_DB ocarsionplusdb
ENV PGPASSWORD ocarsionplus
ENV POSTGRES_USER ocarsionplus

COPY data.sql /docker-entrypoint-initdb.d/