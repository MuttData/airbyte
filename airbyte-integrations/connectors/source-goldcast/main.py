#
# Copyright (c) 2023 Airbyte, Inc., all rights reserved.
#


import sys

from airbyte_cdk.entrypoint import launch
from source_goldcast import SourceGoldcast

if __name__ == "__main__":
    source = SourceGoldcast()
    launch(source, sys.argv[1:])
