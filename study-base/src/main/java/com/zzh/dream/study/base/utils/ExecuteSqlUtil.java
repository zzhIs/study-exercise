package com.zzh.dream.study.base.utils;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


public class ExecuteSqlUtil {

    static final Logger logger = LoggerFactory.getLogger(ExecuteSqlUtil.class);

    private static final String RESOURCE_DATABASE_URL = "jdbc:postgresql://";
    private static final String RESOURCE_DATABASE_USER_NAME = "";
    private static final String RESOURCE_DATABASE_PASSWORD = "";

    private static final String TARGET_DATABASE_URL = "jdbc:postgresql://ip:port/daname";
    private static final String TARGET_DATABASE_USER_NAME = "";
    private static final String TARGET_DATABASE_PASSWORD = "";
    

    private static final String PG_DRIVER = "org.postgresql.Driver";


    private static final String ID_COLUMN_NAME = "id";
    private static String INSERT = "INSERT INTO";//插入sql
    private static String VALUES = "VALUES";//values关键字

    private static final Map<String, SqlEntity> ENTITY_MAP = new HashMap<String, SqlEntity>() {{
        SqlEntity hmsEntity = new SqlEntity();
        hmsEntity.setSource(PG_DRIVER, RESOURCE_DATABASE_URL, RESOURCE_DATABASE_USER_NAME, RESOURCE_DATABASE_PASSWORD);
        hmsEntity.setTarget(PG_DRIVER, TARGET_DATABASE_URL, TARGET_DATABASE_USER_NAME, TARGET_DATABASE_PASSWORD);
        put("HMS", hmsEntity);
    }};

    public static void main(String[] args) {
        //todo 需要同步的表数据查询SQL多个使用\n分割
        String[] sqls = "".split("\n");

        //todo 替换占位符
        List<String> sqlList = splitSql(sqls);
        logger.info("分隔sql完成");
        SqlEntity entity = ENTITY_MAP.get("HMS");
        try {
            for (String sql : sqlList) {
                syncSqlData(sql, entity);
            }
        } finally {
            try {
                entity.executeUpdate(null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            entity.close();
        }

    }

    private static List<String> splitSql(String... sqls) {
        List<String> sqlList = new ArrayList<>();
        String[] medicalNoList = new String[]{"0036b79502954ce2b648e1f0532a79cd","0365bcda1f04409dac2d798119435bb3","04d55c7b194e4f318613d7f80e15901e","068c5af3769949f0acbd062bdbd1060a","075e8a3e1896451da70a4f3305000315","0801b7d08a444e59ac49eea7a546a764","103477d253944980bfa6b1c88ed76e6e","133b0ad9c7e64d6f8cb687c4bc36d78a","1a3799ced81d40dfbe6a2756108e4fda","1a9c7336c15841e4bd3b7f567de58c10","1b28096b99ec4166b54ad1a3546951b0","1c18af0de86c4aceb0a3ddff62f8c444","1d14605f030b46278acf3e187b8ea32c","210d7dd8a7ef462db2284daa83c38104","21ba8198a61146b99b8d819d0b13a4ac","22a8affa1fb64cfc82a1caa7a7671a5e","23547438a5bb48adb903c55c5b99d643","24dfa232babc45f781098ee1e1e9802a","26c9c3ac04364301b186133c0235c3ee","29332ca0b5f34248b83bc227ecedd9d8","2ccabab3321b45a996f5d2a45ac421d5","2e352b4895d34742a595d9bf5169048b","3123664892ce4036ae661d958faa24bc","32b0a8c6fbfe499fa93470d5707fee1c","341eb2d04b254d459eed9b2ea8590af7","3499efaf853e4d168b0da7c07667ca67","39d784601fd349008e2adac67e983877","3b5328b8c9104c2780c1db310e3e7d0f","3c2743b0d7eb42889dd073b01a9274e1","3c3864fa09334945b70d3ace56291f68","3e8c2c0f879345eb8d64e94055d0a4a5","3fbbcef9f57045eb941add052be26515","4124eaf2a27d4707bf0aa7dc393e500b","414ebc97596446d29af99d310ab6bd64","425a7b5f1bae4d53bde4333ab5261aca","4478410d5801406ba4302f397fbb867e","460c70c52564420489a747df084288b0","47ca82882b744a5fbf1bf414afca35c5","48eabe5920b648b99479c6db0f58c8b0","4a73c2dd2c3d4b2b9a8bbc4b31c5bf0c","4bc6019987714db3bd323f87656af9d7","4c1bb3f5fab74d829fc60b98fd045e35","4fa7974e19544ddc86fcc62a522d0a0c","50119c85eba44a0094c71a64f83cb979","50bf463783c04122bc50fbed3534e802","50eceb7415dc499fa2caf6c705502269","5237fb3eabfe42d388614488a6fc5f4f","52748de9f4b14ae0ad3c796443f0f539","539427d64f4646d185b6e5f3e0155df2","541b4d8c1b124d149e000ac7da571692","550243316a1e4364a1ce1491bd136dfa","5574418d5a444abfb3c1cc8d8d12b39c","55dab3731cd64df5b071de08c6f93308","562f1a19c4f1445197c5bcb9f2d3fab5","5862b60d81744b2a9383a55d43108be9","58a71b6b815f4659893835414fb7fc70","59e2a5727a2647ca88e5f44a77d8218c","5b352967727e42ee81773ed6eb84b898","5d878ce6284d4e0a97bb52374ee054cd","5de219928522401e93e26e50f3437f1e","5e0cf3c809184667ac89dff6f910e52e","5f3971f4cc334c259d62e77018f08282","5fdce601b08d4caca307cae33f57295e","61edf35ada4c4840b1963933862e996c","6378e569e71649e88b0e620a99692c77","64c0037464fa4911bcf4c6726364d370","672c3ce78ac84c1984842eae908b4dd1","674b31314d2b4b168a36254a69da61e1","67a82e4b9c224e50a948b82eca55d6e8","67bd0f47a3f445ad80eec2156906ae92","68864e99a8de456fa1526eb6ae95203d","6988e03b5f174a4bbb27823609eb0316","6a41fe454e034243aa52e9b7471e0af6","6a9b541e5c0648489922ba2bd2fdef62","6bc299c7cba54a3ea7e72befeb069993","7166e3978eb7469d886e5c549007822e","72a961536860463f9951c4c5c4f383da","72ac3d070807448588ab2d2514b3c2d6","72d49924aad34379b810d287361b9da0","742c431c218040f896e4c3c576e2fbf9","766451a891d2418eaf6fe2117579291e","7b4cb70488224a4fa3cf987bcfb6f57a","7d3abae0d1d84ae1bcb03ae3eec67f76","7d422557669543c8b24ed937d25093d2","7db8c276a0a44a95a0e91d2511fd2f3f","7ddd0b7195454e6da7b4a40d11514e22","80fd553f0a3b4751be2bc561648d0d08","8459f01f4cd54ef58cc5544a9a1d95fd","84f76248814b47f8a9920bed3719a605","853e5533540244ba8bfb40f78da67c6a","88620bd1333942c2b97b74c9f31b8a5e","88da63b674464bc2bcefe4d5fd21451b","8ba02cea25b5474c9372a178c79fa3bb","8ca2af9acad34ceab2c4163a9daa6b12","8fbc0a9f8f5a464aa2fe0264bd1827d5","8fd4f39389594183a715fb657a47301b","9011c0338a764604b5c9aa0f60bc64e6","9094fa1224384c70b33a51ba089ae954","90de83a0f4ed43038df4864ff4f8fb20","92a1e3c383dc4bdb801e7f282157c1a2","92ce9d36aac04319b842dbf80d7af9a0","9354d91bd18b43c0b409cf57224b9bf2","942d6ad8cf0c4d3595ddf8ac735d1564","99c27441d5c3486fb5ce4422f9b1ad51","9a84462e28324a168ffbb3a175542e63","a005c805882d4ed3b84c0bcb4a123762","a05cf7d72e8349fc8320a851bda102c0","a1dee1b1c77140d89333120368a609af","a2c70ceb6b0f4e92806de42af8c65152","a3a3e55d8fa443b582be5ec9e46a8794","a3bde0b7002b460b92f4ebbcb0ff6a04","a4775d2d98044c7aa454e60a97ae91c2","a66045e44fdb45a897396740a1e82ab3","a885739a54824b93beefcfe0156883fc","aa697b5153024ece94f3359902b14cc2","ab03a6724452486e9db6eb435e22c7d0","ac77fe6c3b034cd4b6d974900b0626d9","ae82866e3b5c4a82a36f893492a5b772","b0ab00bf70fc4b8faa45cc1505a7493c","b2c531c93fdb4c9292e1f18af59181e7","b41f469e359c463c8ab59fd18c576bfa","b438e88fde3549b9a2e97976bed11aac","b879df65ee1246e69377769ed8aca7f6","b8af516b27b840dca77a8954302f70ce","b9da623d2945445e9de7fb1594b51a63","bad98dbd9c6e46d6bd084f3358a05037","bd1bf383bb4a47cb89b1b77e11ed57a0","be67c1a09f6647888d1205101d5277f3","be8c6d46e2624741865b9934bc0ac0eb","c247e2fe4e114b1798480f63886bf9c9","c299e9a4d9d74c68b902825e9ef92571","c2ba42506b264d6fae73a7337a3b8789","c36e3d8ae2ea47b4bf3fd70c0c597dbc","ca371eaf18934e75bf061fd38399ad7f","cb63892d081a4d02a0ae450c8cc897dd","cbeb8e7b6337404781b90f37e9701174","cc319b2bfcd547debc530c37813a7f81","cefdf4d741644c4f999053ef43bdb6b8","d0243265146a41418d616d2e9fca2d66","d3aea89b83c64599adff78b1e88955df","d3d2cc17cbbd483fb7e4882c5d7e2422","d467593eeca6499cbf280efdaa502016","d77a9f35d4fd4bcdb81d6776d08a6251","d869de50b8554c97811136f0c3c36957","d8e7e25037bc4427ab9ac03e5cafd1f5","d9f6bcc5e20549b3b86951219588c4fe","db8ec43aa6a9468b8d812d3df724a9c8","dc29d3c5f3a34e5d91fc2885c5d2c9bb","dd25fbd8863e402e8edc5e9b513d4a38","de6c63242c0b48f7a0c3f9a37fd899fb","df0574b39c514fa4907b481b3efbc398","e73870b17a59444fb3ab3ee8a6232ff7","e7ef97c140d545f1b3f8d20d7da3a28f","e8511a39cfcc4f3a901d530ce79e4573","ed0210a8a95641b290a7ba071d8f8b87","ed2c13407f7144a49487e051bddf1c2a","ef8e45a4f301450688a4895c2b97667b","f0cbb85d0c314a3fbbe72105b282e297","f1d9108beb3445b88c650b4dee2606a8","f47dc396759d4547b8e567450f925e40","f513ff0ec443408eae76c4a0db054bc4","f543509e74864faca04788e6a4f2c08f","f6cb835e5e3a482da56c7f1b3b485c1c","f74317a40d924ed686dbb8c257081c84","fc49ca8859a145448458550c7102fec0","fc7ab1858176466e9a60e9aac9b52c2f","fea7260404964570b93e8ea24d2fcaf1"};
        for (String medicalNo : medicalNoList) {
            for (String sql : sqls) {
                sqlList.add(sql.replaceAll("\\$\\{userId}", medicalNo));
            }
        }
        return sqlList;
    }

    private static String escape(String target) {
        if (target == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == '\'') {
                result.append('\'').append('\'');
            } else {
                result.append(target.charAt(i));
            }
        }
        return result.toString();
    }

    private static void syncSqlData(String sql, SqlEntity sqlEntity) {
        logger.info(String.format("查询sql %s %n", sql));
        try {
            ResultSet rs;
            rs = sqlEntity.getSourceStatement().executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            int index = 1;
            while (rs.next()) {
                StringBuffer ColumnName = new StringBuffer();
                StringBuffer ColumnValue = new StringBuffer();
                String id = null;
                for (int i = 1; i <= columnCount; i++) {
                    String value = escape(rs.getString(i));
                    if (value != null) {
                        value = value.trim();
                    }
                    if (value == null) {
                        value = "null";
                    } else {
                        value = "'" + value + "'";
                    }
                    if (ID_COLUMN_NAME.equals(rsmd.getColumnName(i))) {
                        id = value;
                    }
                    if (i == 1 || i == columnCount) {
                        if (i == columnCount) {
                            ColumnName.append(",");
                        }
                        ColumnName.append(rsmd.getColumnName(i));
                        if (i == 1) {
                            ColumnValue.append(value).append(",");
                        } else {
                            ColumnValue.append(value);
                        }

                    } else {
                        ColumnName.append(",").append(rsmd.getColumnName(i));
                        ColumnValue.append(value).append(",");
                    }
                }
                logger.info("正在处理{}条数据", index++);
                sqlEntity.executeUpdate(deleteSql(id, getTableName(sql)));
                sqlEntity.executeUpdate(insertSQL(ColumnName, ColumnValue, getTableName(sql)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getTableName(String sql) {
        int index = sql.indexOf("from") + 4;
        StringBuilder s = new StringBuilder();
        while (sql.charAt(index) == ' ' || sql.charAt(index) == '\n') {
            index++;
        }
        while (index < sql.length() && sql.charAt(index) != ' ' && sql.charAt(index) != '\n') {
            s.append(sql.charAt(index++));
        }
        return s.toString();
    }

    private static String insertSQL(StringBuffer ColumnName, StringBuffer ColumnValue, String tableName) {
        StringBuffer insertSQL = new StringBuffer();
        insertSQL.append(INSERT).append(" ")
                .append(tableName).append("(").append(ColumnName.toString()).append(")").append(VALUES).append("(").append(ColumnValue.toString().replaceAll(";", "")).append(");");
        System.out.println(insertSQL);
        return insertSQL.toString();
    }

    private static String deleteSql(String idValue, String tableName) {
        return "delete from " + tableName + " where id= " + idValue + ";";
    }


    @Data
    public static class SqlEntity {
        private String sourceDriver;
        private String sourceUrl;
        private String sourceUserName;
        private String sourcePassword;
        private Connection sourceConnection;
        private Statement sourceStatement;

        private String targetDriver;
        private String targetUrl;
        private String targetUserName;
        private String targetPassword;
        private Connection targetConnection;
        private Statement targetStatement;

        private AtomicLong index;

        public SqlEntity() {
            this.index = new AtomicLong(1L);
        }

        public void setSource(String sourceDriver, String sourceUrl, String sourceUserName, String sourcePassword) {
            this.sourceDriver = sourceDriver;
            this.sourceUrl = sourceUrl;
            this.sourceUserName = sourceUserName;
            this.sourcePassword = sourcePassword;
            try {
                Class.forName(sourceDriver).newInstance();
                this.sourceConnection = DriverManager.getConnection(sourceUrl, sourceUserName, sourcePassword);
                this.sourceConnection.setAutoCommit(false);
                this.sourceStatement = this.sourceConnection.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void commitTarget() {
            try {
                this.targetConnection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void close() {
            try {
                if (this.targetConnection != null) {
                    this.targetConnection.close();
                }
                if (this.sourceConnection != null) {
                    this.sourceConnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void executeUpdate(String sql) throws SQLException {
            if (sql == null) {
                this.targetStatement.executeBatch();
                this.targetStatement.clearBatch();
                commitTarget();
            } else {
                this.targetStatement.addBatch(sql);
                if (this.index.getAndAdd(1L) % 50000 == 0) {
                    this.targetStatement.executeBatch();
                    this.targetStatement.clearBatch();
                    commitTarget();
                }
            }
        }

        public void setTarget(String targetDriver, String targetUrl, String targetUserName, String targetPassword) {
            this.targetDriver = targetDriver;
            this.targetUrl = targetUrl;
            this.targetUserName = targetUserName;
            this.targetPassword = targetPassword;
            try {
                Class.forName(this.targetDriver).newInstance();
                this.targetConnection = DriverManager.getConnection(targetUrl, targetUserName, targetPassword);
                this.targetConnection.setAutoCommit(false);
                this.targetStatement = this.targetConnection.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
