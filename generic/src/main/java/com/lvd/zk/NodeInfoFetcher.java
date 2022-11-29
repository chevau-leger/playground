package com.lvd.zk;

import org.apache.zookeeper.ZooKeeper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NodeInfoFetcher {

    public static void main(String[] args) {

        getNodes("/");
//        getNodes();
    }

    private static void getNodes(String path) {

        try {

//            String zookeeper = "10.113.172.204:9701";
            System.out.println("path = " + path);
            String zookeeper = "10.113.172.56:8551";
            ZooKeeper zooKeeper = new ZooKeeper(zookeeper, 5000, null);
            zooKeeper.addAuthInfo("digest", "hsf:Stq@2018".getBytes());
            List<String> children = zooKeeper.getChildren(path, null);

            if (children.isEmpty()) {
                return;
            }
            for (String child : children) {

                if (path.equals("/")) {

                    getNodes(path + child);
                } else {

                    getNodes(path + "/" + child);
                }
            }
        } catch (Exception ignored) {

        }
    }


    private static void getNodes() {

        try {

            List<String> nodes = new ArrayList<>();
            String zookeeper = "10.113.172.204:9701";
            String path = "/dubbo";
            ZooKeeper zooKeeper = new ZooKeeper(zookeeper, 5000, null);
            zooKeeper.addAuthInfo("digest", "hsf:Stq@2018".getBytes());
            List<String> children = zooKeeper.getChildren(path, null);
            for (String child : children) {

                List<String> childNodes = zooKeeper.getChildren(path + "/" + child + "/providers", null);
                List<String> decodeNodes = childNodes.stream().map(item -> item.replaceAll("%3A", ":").replaceAll("%2F", "/")  //过滤URL 包含中文
                        .replaceAll("%3F", "?").replaceAll("%3D", "=").replaceAll(
                                "%26", "&")).collect(Collectors.toList());
                nodes.addAll(decodeNodes);
            }
//            List<String> result = nodes.stream().map(NodeInfoFetcher::parse).collect(Collectors.toList());
            System.out.println(nodes.size());
            for (String node : nodes) {

                System.out.println(parse(node));
            }
        } catch (Exception ignored) {

        }
    }

    private static String parse(String node) {

        try {

            String s = node.replaceFirst("dubbo", "http");
            URL url = new URL(s);
            List<String> query = new ArrayList<>(Arrays.asList(url.getQuery().split("&")));
            Map<String, String> params = query.stream().collect(Collectors.toMap(item -> item.split("=", -1)[0], item -> item.split("=", -1)[1]));
            return url.getHost() + " " + url.getPort() + " " + params.get("interface") + " " + params.get("application");
        } catch (Exception e) {

            return "";
        }
    }
}
