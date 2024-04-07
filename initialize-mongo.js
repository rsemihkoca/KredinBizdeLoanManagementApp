db = db.getSiblingDB('error-logs');
db.createUser({
    user: 'log-consumer',
    pwd: 'example',
    roles: ['readWrite']
});