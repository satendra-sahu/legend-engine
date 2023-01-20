lexer grammar AuthenticationLexerGrammar;

import CoreLexerGrammar;

IMPORT:     'import';

AUTHENTICATION_DEMO:    'AuthenticationDemo';
AUTHENTICATION_DEMO_AUTHENTICATION: 'authentication';

USER_PASSWORD_AUTHENTICATION:   'UserPassword';
USER_PASSWORD_AUTHENTICATION_USERNAME:  'username';
USER_PASSWORD_AUTHENTICATION_PASSWORD:  'password';

API_KEY_AUTHENTICATION:   'ApiKey';
API_KEY_AUTHENTICATION_LOCATION:  'location';
API_KEY_AUTHENTICATION_KEY_NAME:  'keyName';
API_KEY_AUTHENTICATION_VALUE:  'value';

ENCRYPTED_PRIVATE_KEY_AUTHENTICATION:   'EncryptedPrivateKey';
ENCRYPTED_PRIVATE_KEY_PRIVATE_KEY:  'privateKey';
ENCRYPTED_PRIVATE_KEY_PASSPHRASE:  'passphrase';

GCP_WIF_AWS_IDP_AUTHENTICATION:     'GCPWIFWithAWSIdP';
GCP_WIF_AWS_IDP_AUTHENTICATION_SERVICEACCOUNT:     'serviceAccountEmail';
GCP_WIF_AWS_IDP_AUTHENTICATION_AWS_IDP:  'AWSIdP';
GCP_WIF_AWS_IDP_AUTHENTICATION_AWS_IDP_IDP:  'idP';
GCP_WIF_AWS_IDP_AUTHENTICATION_AWS_IDP_REGION: 'region';
GCP_WIF_AWS_IDP_AUTHENTICATION_AWS_IDP_ACCOUNT: 'accountId';
GCP_WIF_AWS_IDP_AUTHENTICATION_AWS_IDP_ROLE: 'role';
GCP_WIF_AWS_IDP_AUTHENTICATION_GCP_WORKLOAD:    'GCPWorkload';
GCP_WIF_AWS_IDP_AUTHENTICATION_GCP_WORKLOAD_WORKLOAD:    'workload';
GCP_WIF_AWS_IDP_AUTHENTICATION_GCP_WORKLOAD_PROJECT_NUMBER:    'projectNumber';
GCP_WIF_AWS_IDP_AUTHENTICATION_GCP_WORKLOAD_POOL_ID:    'poolId';
GCP_WIF_AWS_IDP_AUTHENTICATION_GCP_WORKLOAD_PROVIDER_ID:    'providerId';

PROPERTIES_VAULT_SECRET:    'PropertiesFileSecret';
PROPERTIES_VAULT_SECRET_PROPERTYNAME:    'propertyName';
ENVIRONMENT_VAULT_SECRET:   'EnvironmentSecret';
ENVIRONMENT_VAULT_SECRET_VARIABLENAME:   'envVariableName';
SYSTEM_PROPERTIES_VAULT_SECRET:   'SystemPropertiesSecret';
SYSTEM_PROPERTIES_VAULT_SECRET_PROPERTYNAME:   'systemPropertyName';

AWS_SECRETS_MANAGER_VAULT_SECRET:   'AWSSecretsManagerSecret';
AWS_SECRETS_MANAGER_VAULT_SECRET_SECRETID: 'secretId';
AWS_SECRETS_MANAGER_VAULT_SECRET_VERSIONID: 'versionId';
AWS_SECRETS_MANAGER_VAULT_SECRET_VERSIONSTAGE:  'versionStage';

AWS_CREDENTIALS_STATIC:   'Static';
AWS_CREDENTIALS_STATIC_ACCESSKEYID:   'accessKeyId';
AWS_CREDENTIALS_STATIC_SECRETACCESSKEY:   'secretAccessKey';

AWS_CREDENTIALS_DEFAULT:   'Default';
AWSCREDENTIALS: 'awsCredentials';
