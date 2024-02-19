FROM airbyte/source-facebook-marketing:latest

ENV POETRY_VERSION="1.7"

RUN <<END
    set -e
    pip install -U pip
    # Install poetry
    curl -sSL https://install.python-poetry.org | POETRY_VERSION=${POETRY_VERSION} python3 -
    poetry config virtualenvs.create false
END

COPY . ./airbyte/integration_code
COPY source_facebook_marketing ./source_facebook_marketing

WORKDIR ./airbyte/integration_code
# Install dependencies using Poetry
RUN poetry install --with dev

# The entrypoint and default env vars are already set in the base image
ENV AIRBYTE_ENTRYPOINT "python /airbyte/integration_code/main.py"
ENTRYPOINT ["python", "/airbyte/integration_code/main.py"]