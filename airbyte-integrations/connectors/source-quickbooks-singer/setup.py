#
# Copyright (c) 2021 Airbyte, Inc., all rights reserved.
#


from setuptools import find_packages, setup

MAIN_REQUIREMENTS = [
    "tap-quickbooks @ https://github.com/MuttData/tap-quickbooks/tarball/master",
    "airbyte-cdk==0.1.6",
]

TEST_REQUIREMENTS = [
    "pytest~=6.1",
]

setup(
    name="source_quickbooks_singer",
    description="Source implementation for Quickbooks, built on the Singer tap implementation.",
    author="Airbyte",
    author_email="contact@airbyte.io",
    packages=find_packages(),
    install_requires=MAIN_REQUIREMENTS,
    package_data={"": ["*.json"]},
    extras_require={
        "tests": TEST_REQUIREMENTS,
    },
)
