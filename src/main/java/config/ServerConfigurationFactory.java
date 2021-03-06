package config;

import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Properties;
import javax.cache.configuration.Factory;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.QueryEntity;
import org.apache.ignite.cache.store.jdbc.CacheJdbcPojoStoreFactory;
import org.apache.ignite.cache.store.jdbc.JdbcType;
import org.apache.ignite.cache.store.jdbc.JdbcTypeField;
import org.apache.ignite.cache.store.jdbc.dialect.OracleDialect;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

/** This file was generated by Ignite Web Console (01/10/2019, 03:32) **/
public class ServerConfigurationFactory {
    /** Secret properties loading. **/
    private static final Properties props = new Properties();

    static {
        try (InputStream in = IgniteConfiguration.class.getClassLoader().getResourceAsStream("secret.properties")) {
            props.load(in);
        }
        catch (Exception ignored) {
            // No-op.
        }
    }

    /** Helper class for datasource creation. **/
    public static class DataSources {
        public static final OracleDataSource INSTANCE_dsOracle_Orcl = createdsOracle_Orcl();

        private static OracleDataSource createdsOracle_Orcl() {
            try {
                OracleDataSource dsOracle_Orcl = new OracleDataSource();

                dsOracle_Orcl.setURL(props.getProperty("dsOracle_Orcl.jdbc.url"));
                dsOracle_Orcl.setUser(props.getProperty("dsOracle_Orcl.jdbc.username"));
                dsOracle_Orcl.setPassword(props.getProperty("dsOracle_Orcl.jdbc.password"));

                return dsOracle_Orcl;
            }
            catch (SQLException ex) {
                throw new Error(ex);
            }
        }
    }

    /**
     * Configure grid.
     * 
     * @return Ignite configuration.
     * @throws Exception If failed to construct Ignite configuration instance.
     **/
    public static IgniteConfiguration createConfiguration() throws Exception {
        IgniteConfiguration cfg = new IgniteConfiguration();

        cfg.setIgniteInstanceName("hue-oracle");

        TcpDiscoverySpi discovery = new TcpDiscoverySpi();

        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();

        ipFinder.setAddresses(Arrays.asList("127.0.0.1:47500..47510"));

        discovery.setIpFinder(ipFinder);

        cfg.setDiscoverySpi(discovery);

        DataStorageConfiguration dataStorageCfg = new DataStorageConfiguration();

        DataRegionConfiguration dataRegionCfg = new DataRegionConfiguration();

        dataRegionCfg.setPersistenceEnabled(true);

        dataStorageCfg.setDefaultDataRegionConfiguration(dataRegionCfg);

        cfg.setDataStorageConfiguration(dataStorageCfg);

        cfg.setCacheConfiguration(
            cacheFeedSapEmployeeCache(),
            cacheFeedSapEmployeeCache1(),
            cacheFeedSapEmployeeCache2()
        );

        return cfg;
    }

    /**
     * Create configuration for cache "FeedSapEmployeeCache".
     * 
     * @return Configured cache.
     * @throws Exception if failed to create cache configuration.
     **/
    public static CacheConfiguration cacheFeedSapEmployeeCache() throws Exception {
        CacheConfiguration ccfg = new CacheConfiguration();

        ccfg.setName("FeedSapEmployeeCache");
        ccfg.setCacheMode(CacheMode.REPLICATED);
        ccfg.setAtomicityMode(CacheAtomicityMode.ATOMIC);

        CacheJdbcPojoStoreFactory cacheStoreFactory = new CacheJdbcPojoStoreFactory();

        cacheStoreFactory.setDataSourceFactory(new Factory<DataSource>() {
            /** {@inheritDoc} **/
            @Override public DataSource create() {
                return DataSources.INSTANCE_dsOracle_Orcl;
            };
        });

        cacheStoreFactory.setDialect(new OracleDialect());

        ccfg.setCacheStoreFactory(cacheStoreFactory);

        ccfg.setReadThrough(true);
        ccfg.setWriteThrough(true);

        return ccfg;
    }

    /**
     * Create configuration for cache "FeedSapEmployeeCache1".
     * 
     * @return Configured cache.
     * @throws Exception if failed to create cache configuration.
     **/
    public static CacheConfiguration cacheFeedSapEmployeeCache1() throws Exception {
        CacheConfiguration ccfg = new CacheConfiguration();

        ccfg.setName("FeedSapEmployeeCache1");
        ccfg.setCacheMode(CacheMode.REPLICATED);
        ccfg.setAtomicityMode(CacheAtomicityMode.ATOMIC);

        CacheJdbcPojoStoreFactory cacheStoreFactory = new CacheJdbcPojoStoreFactory();

        cacheStoreFactory.setDataSourceFactory(new Factory<DataSource>() {
            /** {@inheritDoc} **/
            @Override public DataSource create() {
                return DataSources.INSTANCE_dsOracle_Orcl;
            };
        });

        cacheStoreFactory.setDialect(new OracleDialect());

        ccfg.setCacheStoreFactory(cacheStoreFactory);

        ccfg.setReadThrough(true);
        ccfg.setWriteThrough(true);

        return ccfg;
    }

    /**
     * Create configuration for cache "FeedSapEmployeeCache2".
     * 
     * @return Configured cache.
     * @throws Exception if failed to create cache configuration.
     **/
    public static CacheConfiguration cacheFeedSapEmployeeCache2() throws Exception {
        CacheConfiguration ccfg = new CacheConfiguration();

        ccfg.setName("FeedSapEmployeeCache2");
        ccfg.setCacheMode(CacheMode.PARTITIONED);
        ccfg.setAtomicityMode(CacheAtomicityMode.ATOMIC);

        CacheJdbcPojoStoreFactory cacheStoreFactory = new CacheJdbcPojoStoreFactory();

        cacheStoreFactory.setDataSourceFactory(new Factory<DataSource>() {
            /** {@inheritDoc} **/
            @Override public DataSource create() {
                return DataSources.INSTANCE_dsOracle_Orcl;
            };
        });

        cacheStoreFactory.setDialect(new OracleDialect());

        cacheStoreFactory.setTypes(jdbcTypeFeedSapEmployee(ccfg.getName()));

        ccfg.setCacheStoreFactory(cacheStoreFactory);

        ccfg.setReadThrough(true);
        ccfg.setWriteThrough(true);

        ArrayList<QueryEntity> qryEntities = new ArrayList<>();

        QueryEntity qryEntity = new QueryEntity();

        qryEntity.setKeyType("java.lang.String");
        qryEntity.setValueType("model.FeedSapEmployee");
        qryEntity.setTableName("FEED_SAP_EMPLOYEE");
        qryEntity.setKeyFieldName("personalid");

        HashSet<String> keyFields = new HashSet<>();

        keyFields.add("personalid");

        qryEntity.setKeyFields(keyFields);

        LinkedHashMap<String, String> fields = new LinkedHashMap<>();

        fields.put("sevendigitstaffid", "java.lang.Long");
        fields.put("forename", "java.lang.String");
        fields.put("surname", "java.lang.String");
        fields.put("sapcostcentrecompany", "java.lang.String");
        fields.put("startdt", "java.lang.String");
        fields.put("enddt", "java.lang.String");
        fields.put("corporatetitle", "java.lang.String");
        fields.put("globallevel", "java.lang.String");
        fields.put("stafftype", "java.lang.String");
        fields.put("staffsubtype", "java.lang.String");
        fields.put("staffstatus", "java.lang.String");
        fields.put("staffclass", "java.lang.String");
        fields.put("globalstaffid", "java.lang.Long");
        fields.put("taxnumber", "java.lang.String");
        fields.put("orgunit", "java.lang.String");
        fields.put("orgunitabbv", "java.lang.String");
        fields.put("orgunitdesc", "java.lang.String");
        fields.put("dept", "java.lang.String");
        fields.put("deptabbv", "java.lang.String");
        fields.put("deptdesc", "java.lang.String");
        fields.put("buildingdesc", "java.lang.String");
        fields.put("countrydesc", "java.lang.String");
        fields.put("firstname", "java.lang.String");
        fields.put("jobkey", "java.lang.String");
        fields.put("linemanagerid", "java.lang.String");
        fields.put("previouslastname", "java.lang.String");
        fields.put("gender", "java.lang.String");
        fields.put("lastdayworked", "java.lang.String");
        fields.put("emailaddr", "java.lang.String");
        fields.put("previouscostcentre", "java.lang.String");
        fields.put("lastdaypaid", "java.lang.String");
        fields.put("dateofbirth", "java.lang.String");
        fields.put("nationalitycode", "java.lang.String");
        fields.put("maternitydatefrom", "java.lang.String");
        fields.put("maternitydateto", "java.lang.String");
        fields.put("absence", "java.lang.String");
        fields.put("entitycode", "java.lang.String");
        fields.put("entitytext", "java.lang.String");
        fields.put("busarea1", "java.lang.String");
        fields.put("busareaabbr1", "java.lang.String");
        fields.put("busareadesc1", "java.lang.String");
        fields.put("busarea2", "java.lang.String");
        fields.put("busareaabbr2", "java.lang.String");
        fields.put("busareadesc2", "java.lang.String");
        fields.put("busarea3", "java.lang.String");
        fields.put("busareaabbr3", "java.lang.String");
        fields.put("busareadesc3", "java.lang.String");
        fields.put("busarea4", "java.lang.String");
        fields.put("busareaabbr4", "java.lang.String");
        fields.put("busareadesc4", "java.lang.String");
        fields.put("leavingcode", "java.lang.String");
        fields.put("graduatestartdate", "java.lang.String");
        fields.put("localhirerehire", "java.lang.String");
        fields.put("localsupervisor", "java.lang.String");
        fields.put("linemanagerbrid", "java.lang.String");
        fields.put("brid", "java.lang.String");
        fields.put("personnelarea", "java.lang.String");
        fields.put("personnelareadesc", "java.lang.String");
        fields.put("busarea5", "java.lang.String");
        fields.put("busareaabbr5", "java.lang.String");
        fields.put("busareadesc5", "java.lang.String");
        fields.put("subfunction", "java.lang.String");
        fields.put("subfunctiondesc", "java.lang.String");
        fields.put("function", "java.lang.String");
        fields.put("functiondesc", "java.lang.String");
        fields.put("sapcostcentretext", "java.lang.String");
        fields.put("companycode", "java.lang.String");
        fields.put("companycodetxt", "java.lang.String");
        fields.put("operationalstatusind", "java.lang.String");
        fields.put("citizencard", "java.lang.String");
        fields.put("evetelephoneno", "java.lang.String");
        fields.put("identificationcard", "java.lang.String");
        fields.put("pannumber", "java.lang.String");
        fields.put("nationalidcardno", "java.lang.String");
        fields.put("labourcardnumberid", "java.lang.String");
        fields.put("personalmobceltelephoneno", "java.lang.String");
        fields.put("employmentpass", "java.lang.String");
        fields.put("singaporeblue", "java.lang.String");
        fields.put("passport", "java.lang.String");
        fields.put("hongkongidentityno", "java.lang.String");
        fields.put("uniquenationalid", "java.lang.String");
        fields.put("singaporepink", "java.lang.String");
        fields.put("visaworkpermitreftier2", "java.lang.String");
        fields.put("busarea6", "java.lang.String");
        fields.put("busareaabbr6", "java.lang.String");
        fields.put("busareadesc6", "java.lang.String");
        fields.put("busarea7", "java.lang.String");
        fields.put("busareaabbr7", "java.lang.String");
        fields.put("busareadesc7", "java.lang.String");
        fields.put("busarea8", "java.lang.String");
        fields.put("busareaabbr8", "java.lang.String");
        fields.put("busareadesc8", "java.lang.String");
        fields.put("busarea9", "java.lang.String");
        fields.put("busareaabbr9", "java.lang.String");
        fields.put("busareadesc9", "java.lang.String");
        fields.put("busarea10", "java.lang.String");
        fields.put("busareaabbr10", "java.lang.String");
        fields.put("busareadesc10", "java.lang.String");
        fields.put("opnonopstatusreason", "java.lang.String");
        fields.put("opnonopstartdate", "java.lang.String");
        fields.put("opnonopenddate", "java.lang.String");
        fields.put("personalid", "java.lang.String");

        qryEntity.setFields(fields);
        qryEntities.add(qryEntity);

        ccfg.setQueryEntities(qryEntities);

        return ccfg;
    }

    /**
     * Create JDBC type for "jdbcTypeFeedSapEmployee".
     * 
     * @param cacheName Cache name.
     * @return Configured JDBC type.
     **/
    private static JdbcType jdbcTypeFeedSapEmployee(String cacheName) {
        JdbcType type = new JdbcType();

        type.setCacheName(cacheName);
        type.setKeyType(String.class);
        type.setValueType("model.FeedSapEmployee");
        type.setDatabaseSchema("NIKHIL");
        type.setDatabaseTable("FEED_SAP_EMPLOYEE");

        type.setKeyFields(new JdbcTypeField(Types.VARCHAR, "PERSONALID", String.class, "personalid"));

        type.setValueFields(
            new JdbcTypeField(Types.BIGINT, "SEVENDIGITSTAFFID", Long.class, "sevendigitstaffid"),
            new JdbcTypeField(Types.VARCHAR, "FORENAME", String.class, "forename"),
            new JdbcTypeField(Types.VARCHAR, "SURNAME", String.class, "surname"),
            new JdbcTypeField(Types.VARCHAR, "SAPCOSTCENTRECOMPANY", String.class, "sapcostcentrecompany"),
            new JdbcTypeField(Types.VARCHAR, "STARTDT", String.class, "startdt"),
            new JdbcTypeField(Types.VARCHAR, "ENDDT", String.class, "enddt"),
            new JdbcTypeField(Types.VARCHAR, "CORPORATETITLE", String.class, "corporatetitle"),
            new JdbcTypeField(Types.VARCHAR, "GLOBALLEVEL", String.class, "globallevel"),
            new JdbcTypeField(Types.VARCHAR, "STAFFTYPE", String.class, "stafftype"),
            new JdbcTypeField(Types.VARCHAR, "STAFFSUBTYPE", String.class, "staffsubtype"),
            new JdbcTypeField(Types.VARCHAR, "STAFFSTATUS", String.class, "staffstatus"),
            new JdbcTypeField(Types.VARCHAR, "STAFFCLASS", String.class, "staffclass"),
            new JdbcTypeField(Types.BIGINT, "GLOBALSTAFFID", Long.class, "globalstaffid"),
            new JdbcTypeField(Types.VARCHAR, "TAXNUMBER", String.class, "taxnumber"),
            new JdbcTypeField(Types.VARCHAR, "ORGUNIT", String.class, "orgunit"),
            new JdbcTypeField(Types.VARCHAR, "ORGUNITABBV", String.class, "orgunitabbv"),
            new JdbcTypeField(Types.VARCHAR, "ORGUNITDESC", String.class, "orgunitdesc"),
            new JdbcTypeField(Types.VARCHAR, "DEPT", String.class, "dept"),
            new JdbcTypeField(Types.VARCHAR, "DEPTABBV", String.class, "deptabbv"),
            new JdbcTypeField(Types.VARCHAR, "DEPTDESC", String.class, "deptdesc"),
            new JdbcTypeField(Types.VARCHAR, "BUILDINGDESC", String.class, "buildingdesc"),
            new JdbcTypeField(Types.VARCHAR, "COUNTRYDESC", String.class, "countrydesc"),
            new JdbcTypeField(Types.VARCHAR, "FIRSTNAME", String.class, "firstname"),
            new JdbcTypeField(Types.VARCHAR, "JOBKEY", String.class, "jobkey"),
            new JdbcTypeField(Types.VARCHAR, "LINEMANAGERID", String.class, "linemanagerid"),
            new JdbcTypeField(Types.VARCHAR, "PREVIOUSLASTNAME", String.class, "previouslastname"),
            new JdbcTypeField(Types.CHAR, "GENDER", String.class, "gender"),
            new JdbcTypeField(Types.VARCHAR, "LASTDAYWORKED", String.class, "lastdayworked"),
            new JdbcTypeField(Types.VARCHAR, "EMAILADDR", String.class, "emailaddr"),
            new JdbcTypeField(Types.VARCHAR, "PREVIOUSCOSTCENTRE", String.class, "previouscostcentre"),
            new JdbcTypeField(Types.VARCHAR, "LASTDAYPAID", String.class, "lastdaypaid"),
            new JdbcTypeField(Types.VARCHAR, "DATEOFBIRTH", String.class, "dateofbirth"),
            new JdbcTypeField(Types.VARCHAR, "NATIONALITYCODE", String.class, "nationalitycode"),
            new JdbcTypeField(Types.VARCHAR, "MATERNITYDATEFROM", String.class, "maternitydatefrom"),
            new JdbcTypeField(Types.VARCHAR, "MATERNITYDATETO", String.class, "maternitydateto"),
            new JdbcTypeField(Types.VARCHAR, "ABSENCE", String.class, "absence"),
            new JdbcTypeField(Types.VARCHAR, "ENTITYCODE", String.class, "entitycode"),
            new JdbcTypeField(Types.VARCHAR, "ENTITYTEXT", String.class, "entitytext"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREA1", String.class, "busarea1"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREAABBR1", String.class, "busareaabbr1"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREADESC1", String.class, "busareadesc1"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREA2", String.class, "busarea2"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREAABBR2", String.class, "busareaabbr2"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREADESC2", String.class, "busareadesc2"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREA3", String.class, "busarea3"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREAABBR3", String.class, "busareaabbr3"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREADESC3", String.class, "busareadesc3"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREA4", String.class, "busarea4"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREAABBR4", String.class, "busareaabbr4"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREADESC4", String.class, "busareadesc4"),
            new JdbcTypeField(Types.VARCHAR, "LEAVINGCODE", String.class, "leavingcode"),
            new JdbcTypeField(Types.VARCHAR, "GRADUATESTARTDATE", String.class, "graduatestartdate"),
            new JdbcTypeField(Types.VARCHAR, "LOCALHIREREHIRE", String.class, "localhirerehire"),
            new JdbcTypeField(Types.VARCHAR, "LOCALSUPERVISOR", String.class, "localsupervisor"),
            new JdbcTypeField(Types.VARCHAR, "LINEMANAGERBRID", String.class, "linemanagerbrid"),
            new JdbcTypeField(Types.VARCHAR, "BRID", String.class, "brid"),
            new JdbcTypeField(Types.VARCHAR, "PERSONNELAREA", String.class, "personnelarea"),
            new JdbcTypeField(Types.VARCHAR, "PERSONNELAREADESC", String.class, "personnelareadesc"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREA5", String.class, "busarea5"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREAABBR5", String.class, "busareaabbr5"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREADESC5", String.class, "busareadesc5"),
            new JdbcTypeField(Types.VARCHAR, "SUBFUNCTION", String.class, "subfunction"),
            new JdbcTypeField(Types.VARCHAR, "SUBFUNCTIONDESC", String.class, "subfunctiondesc"),
            new JdbcTypeField(Types.VARCHAR, "FUNCTION", String.class, "function"),
            new JdbcTypeField(Types.VARCHAR, "FUNCTIONDESC", String.class, "functiondesc"),
            new JdbcTypeField(Types.VARCHAR, "SAPCOSTCENTRETEXT", String.class, "sapcostcentretext"),
            new JdbcTypeField(Types.VARCHAR, "COMPANYCODE", String.class, "companycode"),
            new JdbcTypeField(Types.VARCHAR, "COMPANYCODETXT", String.class, "companycodetxt"),
            new JdbcTypeField(Types.VARCHAR, "OPERATIONALSTATUSIND", String.class, "operationalstatusind"),
            new JdbcTypeField(Types.VARCHAR, "CITIZENCARD", String.class, "citizencard"),
            new JdbcTypeField(Types.VARCHAR, "EVETELEPHONENO", String.class, "evetelephoneno"),
            new JdbcTypeField(Types.VARCHAR, "IDENTIFICATIONCARD", String.class, "identificationcard"),
            new JdbcTypeField(Types.VARCHAR, "PANNUMBER", String.class, "pannumber"),
            new JdbcTypeField(Types.VARCHAR, "NATIONALIDCARDNO", String.class, "nationalidcardno"),
            new JdbcTypeField(Types.VARCHAR, "LABOURCARDNUMBERID", String.class, "labourcardnumberid"),
            new JdbcTypeField(Types.VARCHAR, "PERSONALMOBCELTELEPHONENO", String.class, "personalmobceltelephoneno"),
            new JdbcTypeField(Types.VARCHAR, "EMPLOYMENTPASS", String.class, "employmentpass"),
            new JdbcTypeField(Types.VARCHAR, "SINGAPOREBLUE", String.class, "singaporeblue"),
            new JdbcTypeField(Types.VARCHAR, "PASSPORT", String.class, "passport"),
            new JdbcTypeField(Types.VARCHAR, "HONGKONGIDENTITYNO", String.class, "hongkongidentityno"),
            new JdbcTypeField(Types.VARCHAR, "UNIQUENATIONALID", String.class, "uniquenationalid"),
            new JdbcTypeField(Types.VARCHAR, "SINGAPOREPINK", String.class, "singaporepink"),
            new JdbcTypeField(Types.VARCHAR, "VISAWORKPERMITREFTIER2", String.class, "visaworkpermitreftier2"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREA6", String.class, "busarea6"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREAABBR6", String.class, "busareaabbr6"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREADESC6", String.class, "busareadesc6"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREA7", String.class, "busarea7"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREAABBR7", String.class, "busareaabbr7"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREADESC7", String.class, "busareadesc7"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREA8", String.class, "busarea8"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREAABBR8", String.class, "busareaabbr8"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREADESC8", String.class, "busareadesc8"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREA9", String.class, "busarea9"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREAABBR9", String.class, "busareaabbr9"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREADESC9", String.class, "busareadesc9"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREA10", String.class, "busarea10"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREAABBR10", String.class, "busareaabbr10"),
            new JdbcTypeField(Types.VARCHAR, "BUSAREADESC10", String.class, "busareadesc10"),
            new JdbcTypeField(Types.VARCHAR, "OPNONOPSTATUSREASON", String.class, "opnonopstatusreason"),
            new JdbcTypeField(Types.VARCHAR, "OPNONOPSTARTDATE", String.class, "opnonopstartdate"),
            new JdbcTypeField(Types.VARCHAR, "OPNONOPENDDATE", String.class, "opnonopenddate")
        );

        return type;
    }
}