## Build

Use <em>mvn install</em> to build the project.  

Use <em>mvn</em> site to generate the maven site. 

Coverage report is in the maven site: Project Reports -> Cobertura Test Coverage

## Usage

### Start the application

Hum...... this is realy a beta version, please input: 

<em>mvn exec:java -Dlogback.configurationFile=logback.xml -Dexec.mainClass=com.ericsson.javatraining.contacts.ContactFrame</em>

### Add contact:   

Fill in the "Name", "Phone Number", "Address" field, and click "Add contact" button.  

###Search contact:  

Fill in the "Search Key" field, and click "Search contact" button.    

###Delete contact: 

Fill in the "Search Key" field, and click "Delete contact" button, all the records which match the search key will be delete. Strongly recommended you search first.  


## Logs

Operation logs is recorded in the logs directory.  
