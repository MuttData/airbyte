# HTTP Request

:::caution

This connector is graveyarded and will not be receiving any updates from the Airbyte team. Its functionalities have been replaced by the [Airbyte CDK](../../platform/connector-development/cdk-python/), which allows you to create source connectors for any HTTP API.

:::

## Overview

This connector allows you to generally connect to any HTTP API. In order to use this connector, you must manually bring it in as a custom connector. The steps to do this can be found [here](/platform/connector-development/tutorials/custom-python-connector/getting-started).

## Where do I find the Docker image?

The Docker image for the HTTP Request connector image can be found at our DockerHub [here](https://hub.docker.com/r/airbyte/source-http-request).

## Why was this connector graveyarded?

We found that there are lots of cases in which using a general connector leads to poor user experience, as there are countless edge cases for different API structures, different authentication policies, and varied approaches to rate-limiting. We believe that enabling users to more easily
create connectors is a more scalable and resilient approach to maximizing the quality of the user experience.
