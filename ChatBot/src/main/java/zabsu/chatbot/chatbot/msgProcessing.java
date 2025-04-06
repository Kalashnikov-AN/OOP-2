package zabsu.chatbot.chatbot;

import java.util.*;
import java.util.regex.Pattern;

public class msgProcessing implements  ImsgProcessing {

    List<IAnswer> ans = new ArrayList<>();

    public msgProcessing(){
        ans.add(new TextAnswer(List.of("Привет", "Hello"), Pattern.compile("hi|hello|привет|здравствуйте")));
        ans.add(new TextAnswer(List.of("Я чат-бот помощник!"), Pattern.compile("кто\\s.*ты|ты\\s.*кто|как\\s.*зовут|как\\s.*имя")));
        ans.add(new TextAnswer(List.of("До свидания!", "Пока!", "До новых встреч!"), Pattern.compile(".*до свидания|.*пока")));
        ans.add(new TextAnswer(List.of(new Date().toString()), Pattern.compile("который\\s.*час|сколько\\s.*время")));
        ans.add(new MathAnswer());
        ans.add(new HTTPAnswer());
    }

    public String answer(String s){
        String temp = String.join(" ", s.toLowerCase().split("[ {,|.}?!]+"));
        System.out.println(temp);
        for (int i = 0; i < ans.size(); i++){
            IAnswer cur_ans = ans.get(i);
            if (cur_ans.is_matched(temp))
                    return cur_ans.answer(temp);
        }
        return "К сожалению, пока что бот не умеет отвечать на такие вопросы";
    }

    }

