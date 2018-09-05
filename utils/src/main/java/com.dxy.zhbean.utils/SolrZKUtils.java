package com.dxy.zhbean.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.cloud.SolrZkClient;
import org.apache.solr.common.cloud.ZkStateReader;
import org.eclipse.jetty.util.StringUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/9/5
 */
@Slf4j
public class SolrZKUtils {

        static String zkHost = "localhost:2181,localhost:2182,localhost:2183";

    /**
     * 删除zk上config
     */
    public static void deleteConfigs(String configName, String zkHost) {
        String configZnode = "/configs/" + configName;
        try (CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder(Collections.singletonList(zkHost), Optional.empty()).build()) {

            ZkStateReader zkStateReader = cloudSolrClient.getZkStateReader();
            if (isConfigExists(configName, zkHost)) {
                zkStateReader.getZkClient().clean(configZnode);
                System.out.println("delete " + configName + " success!");
            } else {
                System.out.println("delete " + configName + "failure! " + configName + "is not exists!");
            }
        } catch (Exception exc) {
            System.out.println("\nWARNING: Failed to delete configuration directory " + configZnode + " in ZooKeeper due to: " +
                    exc.getMessage() + "\nYou'll need to manually delete this znode using the zkcli script.");
        }

    }

    /**
     * 批量删除zk上的配置
     */
    public static void batchDeleteConfigs(String configNames, String zkHost) {
        if (StringUtil.isNotBlank(configNames.trim())) {
            String[] args = configNames.split(",");
            for (String s : args) {
                deleteConfigs(s, zkHost);
            }
        }
    }

    /**
     * 获取zk上所有configs
     */
    public static String listConfigs(String zkHost) {
        try (CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder(Collections.singletonList(zkHost), Optional.empty()).build()) {
            ZkStateReader zkStateReader = cloudSolrClient.getZkStateReader();
            String s = zkStateReader.getZkClient().listZnode("/configs", false);
            String params = s.replaceAll(System.lineSeparator(), ",");
            System.out.println(params);
            return params;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    /**
     * 判断是否存在某个config
     *
     * @param configName
     */
    public static boolean isConfigExists(String configName, String zkHost) {
        boolean flag = false;
        try (CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder(Collections.singletonList(zkHost), Optional.empty()).build()) {
            flag = cloudSolrClient.getZkStateReader().getZkClient().exists("/configs/" + configName, true);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 获取zk上所有collections
     */
    public static String listCollections(String zkHost) {
        try (CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder(Collections.singletonList(zkHost), Optional.empty()).build()) {
            ZkStateReader zkStateReader = cloudSolrClient.getZkStateReader();
            String s = zkStateReader.getZkClient().listZnode("/collections", false);
            String params = s.replaceAll(System.lineSeparator(), ",");
            return params;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 删除zk上collection
     */
    public static void deleteCollection(String collectionName, Boolean isDelConfig, String zkHost) {
        String configZnode = "/collections/" + collectionName;
        try (CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder(Collections.singletonList(zkHost), Optional.empty()).build()) {

            ZkStateReader zkStateReader = cloudSolrClient.getZkStateReader();
            if (isCollectionExists(collectionName, zkHost)) {
                zkStateReader.getZkClient().clean(configZnode);
                System.out.println("delete " + collectionName + " success!");
            } else {
                System.out.println("delete " + collectionName + "failure! " + collectionName + "is not exists!");
            }
        } catch (Exception exc) {
            System.out.println("\nWARNING: Failed to delete configuration directory " + configZnode + " in ZooKeeper due to: " +
                    exc.getMessage() + "\nYou'll need to manually delete this znode using the zkcli script.");
        }

    }

    /**
     * 判断是否存在某个collection
     *
     * @param collectionName
     */
    public static boolean isCollectionExists(String collectionName, String zkHost) {
        boolean flag = false;
        try (CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder(Collections.singletonList(zkHost), Optional.empty()).build()) {
            flag = cloudSolrClient.getZkStateReader().getZkClient().exists("/collections/" + collectionName, true);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 下载collection的配置文件
     *
     * @param collection
     */
    public static void downCollectionConfsets(String collection, String localConfigDir) throws IOException {
        String zkHost = "";
        Path localConfigPath = Paths.get(localConfigDir);
        if (localConfigPath.endsWith("/conf") == false) {
            localConfigPath = Paths.get(localConfigPath.toString(), "conf");
        }
        if (Files.exists(localConfigPath) == false) {
            Files.createDirectories(localConfigPath);
        }
        try (SolrZkClient zkClient = new SolrZkClient(zkHost, 30000)) {
            log.info("Downloading configset " + collection + " from ZooKeeper at " + zkHost +
                    " to directory " + localConfigPath.toAbsolutePath());
            zkClient.downConfig(collection, localConfigPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
