A simple web application that allows a user to create ad campaigns.

###############################
#
# BUILD PROGRAM & RUN
#
# - executes unit tests
# - generates jar
###############################
<project_dir> $ ./gradlew build
<project_dir> $ java -jar ./build/libs/advertisement-campaign-services-0.0.1-SNAPSHOT.jar




###############################
#
# RUN INTEGRATION TESTS
#
###############################
<project_dir> $ newman run ./integration-test/ad-campaign-integration-test.postman_collection.json

###############################
#
# REQUIRES BACKEND DB (MYSQL/ORACLE/etc)
#
###############################
Modify src/main/resources/application.properties to point Hibernate to DB
