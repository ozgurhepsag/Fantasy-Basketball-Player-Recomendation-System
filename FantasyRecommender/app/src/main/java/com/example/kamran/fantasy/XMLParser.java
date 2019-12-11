package com.example.kamran.fantasy;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;


public class XMLParser {

    // parse all players and teams
    public static Team parseTeam(String teamXML) throws XmlPullParserException, IOException {
        ArrayList<Player> roster = new ArrayList<Player>();
        String teamID = "";
        Player player = null;

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();

        parser.setInput( new StringReader( teamXML ) );
        int eventType = parser.getEventType();
        String text = "";

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagname = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (tagname.equalsIgnoreCase("player")) {
                        player = new Player();
                    }
                    break;

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (tagname.equalsIgnoreCase("team_id")) {
                        teamID = text;
                    } else if (tagname.equalsIgnoreCase("full")) {
                        player.setName(text);
                    } else if (tagname.equalsIgnoreCase("image_url") && player != null) {
                        player.setImageUrl(text);
                    } else if (tagname.equalsIgnoreCase("editorial_team_abbr")) {
                        player.setTeam(text.toUpperCase());
                    } else if (tagname.equalsIgnoreCase("display_position")) {
                        String[] tokens = text.split(",");
                        player.setPositions(tokens);
                    } else if (tagname.equalsIgnoreCase("player")) {
                        roster.add(player);
                        player = null;
                    }
                    break;

                default:
                    break;
            }
            eventType = parser.next();
        }

        return new Team(roster, teamID);
    }

    // parse required user league info
    public static UserInfo parseLeagueInfo(String leagueXML, UserInfo user) throws XmlPullParserException, IOException {

        String lagueID = "";
        String numberOfTeams = "";
        String username = "";

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();

        parser.setInput( new StringReader( leagueXML ) );
        int eventType = parser.getEventType();
        String text = "";

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagname = parser.getName();
            switch (eventType) {

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (tagname.equalsIgnoreCase("league_id")) {
                        lagueID = text;
                    } else if (tagname.equalsIgnoreCase("num_teams")) {
                        numberOfTeams = text;
                    } else if (tagname.equalsIgnoreCase("name")) {
                        username = text;
                    }

                    break;

                default:
                    break;
            }
            eventType = parser.next();
        }

        user.setLeagueID(lagueID);
        user.setNumberOfTeaminLeague(numberOfTeams);
        user.setUserName(username);
        return user;
    }

    // parse required user team info
    public static UserInfo parseTeamInfo(String teamXML, UserInfo user) throws XmlPullParserException, IOException {

        String teamID = "";
        String teamName = "";

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();

        parser.setInput( new StringReader( teamXML ) );
        int eventType = parser.getEventType();
        String text = "";

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagname = parser.getName();
            switch (eventType) {

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (tagname.equalsIgnoreCase("team_id")) {
                        teamID = text;
                    } else if (tagname.equalsIgnoreCase("name")) {
                        teamName = text;
                    }
                    break;

                default:
                    break;
            }
            eventType = parser.next();
        }

        user.setTeamID(teamID);
        user.setTeamName(teamName);

        return user;
    }

}
