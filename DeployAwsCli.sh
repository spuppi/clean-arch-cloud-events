#!/bin/bash
set -e

### REF. https://dzone.com/articles/aws-lambda-with-api-gateway

region=$AWS_REGION
account_id=$AWS_ACCOUNT_ID
functionName=exampleFunction
apiName=exampleApi

echo "Creating a new API and capturing it's ID ..."
api_id=$(aws apigateway create-rest-api \
   --name "${apiName}" \
   --description "${apiName}" \
   --output text \
   --query 'id')
echo "> API ID is: $api_id"

echo "Storing the API ID on disk - we'll need it later ..."
echo $api_id > api_id.txt

echo "Geting the root resource id for the API ..."
root_id=$(aws apigateway get-resources \
   --rest-api-id "${api_id}" \
   --output text \
   --query 'items[?path==`'/'`].[id]')
echo root_id=$root_id

echo "Creating a resource for the /"${apiName}" path"
resource_id=$(aws apigateway create-resource \
  --rest-api-id "${api_id}" \
  --parent-id "${root_id}" \
  --path-part "${functionName}" | jq -r .id)
echo "Resource id is $resource_id"

echo "Creating the GET method on the /hello resource"
aws apigateway put-method \
  --rest-api-id "${api_id}" \
  --resource-id "${resource_id}" \
  --http-method GET \
  --authorization-type NONE

echo "Integrating the GET method to lambda. Note that the request tempalate uses API Gateway template language to pull in the query parameters as a JSON event for the lambda."
aws apigateway put-integration \
  --rest-api-id "${api_id}" \
  --resource-id "${resource_id}" \
  --http-method GET \
  --type AWS \
  --integration-http-method POST \
  --uri arn:aws:apigateway:${region}:lambda:path/2015-03-31/functions/arn:aws:lambda:${region}:${account_id}:function:"${functionName}"/invocations

echo "Creating a default response for the GET method"
aws apigateway put-method-response \
  --rest-api-id "${api_id}" \
  --resource-id "${resource_id}" \
  --http-method GET \
  --status-code 200

echo "Creating a default response for the integration"
aws apigateway put-integration-response \
  --rest-api-id "${api_id}" \
  --resource-id "${resource_id}" \
  --http-method GET \
  --status-code 200 \
  --selection-pattern ".*"

echo "Adding permission for the API to call the lambda for test so we can use the console to make the api call"
aws lambda add-permission \
  --function-name ${functionName} \
  --statement-id apigateway-"${apiName}"-get-test \
  --action lambda:InvokeFunction \
  --principal apigateway.amazonaws.com \
  --source-arn "arn:aws:execute-api:${region}:${account_id}:${api_id}/*/GET/"${functionName}

echo "Adding permission for the API to call the lambda from any HTTP client"
aws lambda add-permission \
  --function-name ${functionName} \
  --statement-id apigateway-"${apiName}"-get-test \
  --action lambda:InvokeFunction \
  --principal apigateway.amazonaws.com \
  --source-arn "arn:aws:execute-api:${region}:${account_id}:${api_id}/api/GET/hello"

echo "Creating a deployment"
aws apigateway create-deployment \
  --rest-api-id "${api_id}" \
  --stage-name api

echo "All done! you can invoke the api on http://${api_id}.execute-api.${region}.amazonaws.com/api/"${functionName}
