### PluralSight: Spring Framework - Data Access with Spring

# 1. Spring Framework: Overview of Spring Data
- PluralSight: https://app.pluralsight.com/library/courses/spring-framework-overview-spring-data
- Github: 

# 2. Building Applications Using Spring JDBC 5
- PluralSight: https://app.pluralsight.com/library/courses/building-applications-spring-jdbc
- Github: https://github.com/bh5k/ride_tracker
- CRUD: Create - Retrieve - Update - Delete
- Create record: JdbcTemplate, SimpleJdbcInsert, ORM
- Retrieve record: JdbcTemplate, RowMapper, SimpleJdbcCall, PreparedStatementCreateor
- Update record: JdbcTemplate, batchUpdate
- Delete record: JdbcTemplate, NamedParameterJdbcTemplate
- Exception: ExceptionHandler, ServiceError, ThrowError
- Transaction: TransactionManager, @Transactional, Commit/Rollback

# 3. Spring Framework: Spring Data JPA
- PluralSight: https://app.pluralsight.com/library/courses/spring-data-jpa-getting-started
- Github: https://github.com/dlbunker/ps-spring-data-jpa
- JPA (Java Persistence API): have JpaRepository, JPQL (Query DSL)
- JPA Repository: java interface (not class), map 1 to 1 with JPA entity, focus to DAO contract

# 4. Spring Framework: Spring Data JPA 5 with Hibernate
- PluralSight: https://app.pluralsight.com/library/courses/spring-jpa-hibernate
- Github: 

# 5. Spring Framework: Spring Data MongoDB2
- https://app.pluralsight.com/library/courses/spring-framework-data-mongodb/table-of-contents
- Version: MongoDB 2.1.9 (from 2.1.9 to 2.2.13), SpringBoot 2.2.1
- Mongo GUI: Mongo Compass, Robo 3T
- Spring dependency: spring-boot-starter, spring-boot-starter-data-mongodb
- Mongo Data Annotation frequently: @Document @Id @Field @Transient @Indexed @TextIndexed @CompoundIndex @DbRef
ex: @Document(collection='airplanes')
    public class Aircraft { 
        @Id private long id;
        @Indexed(direction=IndexDirection.ASCENDING, unique=false) private string model;
        @Field private int seats;
        @DbRef priavate Manufacturer man;
        ... 
    }
- Mongo Repository Interface: Repository<T, ID>, CrudRepository <T, ID>, PagingAndSortingRepository<T, ID>, MongoRepository<T, ID>
- Mongo Template to execute CRUD: creating, retrieving, updating, deleting documents
- Mongo converter: a feature used for mapping all java types to/from DBObjects when storing or retrieving these objects, or serialization/deserialization of fields
- DBRef format: $ref (name of collection), $id (value of _id field), $db (name of the database)
- 
