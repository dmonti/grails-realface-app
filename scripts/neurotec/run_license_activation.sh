#!/bin/bash

if [ "${UID}" != "0" ]; then
	echo "ERROR: Please run this application with superuser privileges."
	exit 1
fi

if [ ! "${LICENSE_ACTIVATION_DIR}" ]
then
	LICENSE_ACTIVATION_DIR=`dirname "$0"`

	if [ "${LICENSE_ACTIVATION_DIR:0:1}" != "/" ]
	then
		LICENSE_ACTIVATION_DIR="${PWD}/${LICENSE_ACTIVATION_DIR}"
	fi
fi

LIB_DIR="`cd ${LICENSE_ACTIVATION_DIR}/../../../Lib/MacOSX_universal/; pwd`"

cd "${LICENSE_ACTIVATION_DIR}"

DYLD_LIBRARY_PATH="/Library/Frameworks/Neurotechnology/" ./license_activation "$@"

