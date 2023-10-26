FROM airbyte/source-facebook-marketing:latest

COPY . ./airbyte/integration_code
RUN pip install ./airbyte/integration_code

COPY source_facebook_marketing ./source_facebook_marketing

# The entrypoint and default env vars are already set in the base image
ENV AIRBYTE_ENTRYPOINT "python /airbyte/integration_code/main.py"
ENTRYPOINT ["python", "/airbyte/integration_code/main.py"]