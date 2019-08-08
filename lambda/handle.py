from botocore.vendored import requests
import json

def lambda_handler(event, context):
    try:
        data = {
            'parkId': event['placementInfo']['attributes']['parkId'],
            'categoryId': event['placementInfo']['attributes']['categoryId'],
            'description': event['placementInfo']['attributes']['description'],
            'statusId': 1
        }

        r = requests.post('https://national-parks.xyz/api/public/tickets', json=data)

        return {
            'statusCode': r.status_code,
            'body': r.json()
        }
    except Exception as e:
        print('Attempt failed')
        return {
            'statusCode': 400,
            'body': e
        }
