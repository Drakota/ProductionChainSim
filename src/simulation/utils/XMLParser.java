package simulation.utils;

//https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
//https://stackoverflow.com/questions/13295621/recursive-xml-parser

import java.util.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.*;

public class XMLParser {
    private static XMLParser instance = null;

    private List<Map<String, String>> usineTypes = new ArrayList<>();
    private List<Map<String, String>> usines = new ArrayList<>();
    private List<Map<String, String>> roads = new ArrayList<>();

    private XMLParser()
    {
    }

    public static XMLParser getInstance()
    {
        if (instance == null)
            instance = new XMLParser();

        return instance;
    }

    private List<Map<String, String>> parseUsineTypes(NodeList usineNodes) {
        List<Map<String, String>> usinesList = new ArrayList<>();

        for (int i = 0; i < usineNodes.getLength(); i++) {
            Node usineNode = usineNodes.item(i);
            if (usineNode.getNodeType() == Element.ELEMENT_NODE) {
                Map<String, String> uMap = new HashMap<>();
                Element usineElement = (Element) usineNode;
                uMap.put("type", usineElement.getAttribute("type"));

                NodeList iconesNodes = usineElement.getElementsByTagName("icone");
                for (int y = 0; y < iconesNodes.getLength(); y++) {
                    Node iconeNode = iconesNodes.item(y);
                    if (iconeNode.getNodeType() == Element.ELEMENT_NODE) {
                        Element iconeElement = (Element) iconeNode;
                        uMap.put(iconeElement.getAttribute("type"), iconeElement.getAttribute("path"));
                    }
                }
                NodeList sortieNodes = usineElement.getElementsByTagName("sortie");
                if (sortieNodes.getLength() > 0) {
                    Element sortieElement = (Element) sortieNodes.item(0);
                    uMap.put("sortie", sortieElement.getAttribute("type"));
                }
                NodeList entreeNodes = usineElement.getElementsByTagName("entree");
                for (int z = 0; z < entreeNodes.getLength(); z++) {
                    Node entreeNode = entreeNodes.item(z);
                    if (entreeNode.getNodeType() == Element.ELEMENT_NODE) {
                        Element entreeElement = (Element) entreeNode;
                        if (uMap.get("type").equals("entrepot")) {
                            uMap.put(entreeElement.getAttribute("type"), entreeElement.getAttribute("capacite"));
                        }
                        else uMap.put(entreeElement.getAttribute("type"), entreeElement.getAttribute("quantite"));
                    }
                }
                NodeList intervalNodes = usineElement.getElementsByTagName("interval-production");
                if (intervalNodes.getLength() > 0) {
                    Element intervalElement = (Element) intervalNodes.item(0);
                    uMap.put("interval", intervalElement.getTextContent());
                }
                usinesList.add(uMap);
            }
        }
        return usinesList;
    }

    private List<Map<String, String>> parseUsines(NodeList usineNodes) {
        List<Map<String, String>> usinesList = new ArrayList<>();
        for (int i = 0; i < usineNodes.getLength(); i++) {
            Node usineNode = usineNodes.item(i);
            if (usineNode.getNodeType() == Element.ELEMENT_NODE) {
                Map<String, String> uMap = new HashMap<>();
                Element usineElement = (Element) usineNode;

                uMap.put("type", usineElement.getAttribute("type"));
                uMap.put("id", usineElement.getAttribute("id"));
                uMap.put("x", usineElement.getAttribute("x"));
                uMap.put("y", usineElement.getAttribute("y"));
                usinesList.add(uMap);
            }
        }
        return usinesList;
    }

    private List<Map<String, String>> parseRoads(NodeList roadNodes) {
        List<Map<String, String>> roadsList = new ArrayList<>();
        for (int i = 0; i < roadNodes.getLength(); i++) {
            Node roadNode = roadNodes.item(i);
            if (roadNode.getNodeType() == Element.ELEMENT_NODE) {
                Map<String, String> rMap = new HashMap<>();
                Element roadElement = (Element) roadNode;

                rMap.put("de", roadElement.getAttribute("de"));
                rMap.put("vers", roadElement.getAttribute("vers"));
                roadsList.add(rMap);
            }
        }
        return roadsList;
    }

    public Object[] readConfig(String configFile) {
        try {
            File inputFile = new File(configFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Node metadonneesNode = doc.getElementsByTagName("metadonnees").item(0);
            usineTypes = parseUsineTypes(((Element) metadonneesNode).getElementsByTagName("usine"));

            Node simulationNode = doc.getElementsByTagName("simulation").item(0);
            usines = parseUsines(((Element) simulationNode).getElementsByTagName("usine"));
            roads = parseRoads(((Element) simulationNode).getElementsByTagName("chemin"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Object[] {usineTypes, usines, roads};
    }
}
