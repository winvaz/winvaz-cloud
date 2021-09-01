FROM apache-tomcat:8.5.37-jre8
MAINTAINER winvaz
ADD target/*.jar /Users/wdq/Documents/webapps
RUN rm -rf /Users/wdq/Documents/webapps/manager \
&& rm -rf /Users/wdq/Documents/webapps/host-manager \
&& rm -rf /Users/wdq/Documents/webapps/ROOT \
&& echo "Asia/shanghai" > /Users/wdq/Documents/webapps/timezone;