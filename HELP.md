# Learning Spring Boot Framework
- url: https://start.spring.io/
- spring-demo has dependency: Spring Web, Spring Data JPA, Oracle Driver

# Plural Sight
- Create First Spring Boot Application: 
- https://app.pluralsight.com/course-player?clipId=b7bc9b61-098a-4bcc-84db-bee51013d9a2
- https://github.com/dlbunker/ps-first-spring-boot-app


# Spring Boot config and environment
- External Sources: command line parameters, JNDI, OS environment variables
- Internal Sources: servlet parameters, property files, java configuration

# Customize and Override Spring Boot
- Order of precedence:
- 1. Command line args
- 2. SPRING_APPLICATION_JSON args
- 3. Servlet parameters
- 4. JNDI
- 5. Java System Properties
- 6. OS environment variable
- 7. Profile properties
- 8. Application properties
- 9. @PropertySource annotations
- 10. Default properties

# Spring Boot Containerless Architecture
- Original Deployment: Tomcat, Glassfish, Websphere, Spring Boot
- Support Containers: Tomcat, Jetty, Undertow

# Common cloud platform
- cloud foundry
- heroku
- google cloud
- aws
- ms azure

# Plural Sight
- Spring Framework: Overview of Spring Data
- https://app.pluralsight.com/library/courses/spring-data-jpa-getting-started/table-of-contents
- https://github.com/dlbunker/ps-spring-boot-app
- Main modules: JPA, JDBC, MongoDB, Cassandra, Spring Data n
- Community modules: CouchBase, ElasticSearch, Hazelcast, Neo4j, Spring Data n

# ACID Transactional: Atomic, Consistent, Isolated, Durable
- Atomic: it either all happens or does not happen at all
- Consistent: the data written is valid according to the various contraints
- Isolated: other transactions can not see the result until the transaction is committed
- Durable: the results of the transaction are written to disk

# Spring Data: Repository + Query Generation
- https://github.com/dlbunker/ps-spring-data-jpa
- Spring Data Commons: Spring Data JPA, Spring JDBC, Spring Data KeyValue, Spring Data CouchBase, Spring Data Ne04j, Spring Data LDAP, Spring Data MongoDB, Spring Data Redis, Spring Data Hazelcast, Spring Data DynamoDB

# Query DSL (Domain Specific Language = Method Contracts)
- Query DSL is an extensive Java framework, which allows for the generation of type-safe queries in a syntax similar to SQL, it support various backends including JPA, JDO, SQL, Java collections, RDF, Lucene, Hibernate Search, and MongoDB

- Query DSL can begin with: findBy, queryBy, readBy, countBy, getBy
- Query DSL uses JPA attribute names for criteria, multiple criteria combined with [And, Or]

# example query DSL:
- findBySessionNameContains(String name)
- select s from Session s where s.sessionName like :name
- select * from Sessions s where s.session_name like ?

- Keyword: And, Or
- findByFirstNameAndLastName  ||  findByFirstNameOrLastName

- Keyword: Equals, Is, Not
- findBySessionLength || findBySessionLengthIs || findBySessionLengthEquals || findBySessionLengthNot 

- Keyword: Like, Not Like
- findBySessionNameLike("Java%") || findBySessionNameNotLike("Java%")

- Keyword: StartingWith, EndingWith
- other variations: StartsWith, EndsWith, Contains, IsStartingWith, IsEndingWith, IsContaining, NotContaining, NotContains
- findBySessionNameStartingWith("a")  ||  findBySessionNameEndingWith("a")  ||  findBySessionNameContaining("a")

- Keyword: LessThan(Equal), GreaterThan(Equal)
- findBySessionLengthLessThan(30) || findBySessionLengthLessThanEqual(30)  
- findBySessionLengthGreaterThan(30) || findBySessionLengthGreaterThanEqual(30)

- Keyword: Before, After, Between
- findByStartDateBefore(startDate)  ||  findByStartDateAfter(startDate)  ||  findByStartDateBetween(startDate, endDate)

- Keyword: True, False
- findByIncludeWorkshopTrue  ||  findByIncludeWorkshopFalse

- Keyword: IsNull, IsNotNull, NotNull
- findBySpeakerPhotoNull() || findBySpeakerPhotoIsNull() || findBySpeakerPhotoNotNull() || findBySpeakerPhotoIsNotNull()

- Keyword: In, NotIn
- findByCompanyIn(companies) || findByCompanyNotIn(companies)

- Keyword: IgnoreCase
- findByCompanyIgnoreCase(comp) || findByCompanyContainsIgnoreCase(comp)
- where UPPER(a.company) = UPPER(%?%) || where UPPER(a.company) = UPPER(%?1%)

- Keyword: OrderBy
- findByLastNameOrderByFirstNameAsc(name)  || findByLastNameOrderByFirstNameDesc(name)
- WHERE a.lastName = ? ORDER BY a.firstName ASC  ||  WHERE a.lastName = ? ORDER BY a.firstName DESC

- Keyword: First, Top, Distinct
- findFirstByFirstName(name) || findTop5ByFirstName(name) || findDistinctByFirstName(name)
- where firstName = ? LIMIT 5  || SELECT DISTINCT FROM table WHERE firstName = ?

- Keyword: @Query annotation for run: SQL or JPQL
- @Query(value = "select tp from TicketPrice tp where tp.basePrice = ?1")
- @Query(value = "select * from ticket_prices where pricing_category_code = ?1", nativeQuery = true)

- Audit annotations:  @CreatedBy  @LastModifiedBy  @CreatedDate  @LastModifiedDate
