package com.neki_skill.initializer;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neki_skill.entity.Skill;
import com.neki_skill.repositories.SkillRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {


    @Autowired
    private SkillRepository skillRepository;

    @PostConstruct
    public void init() {
        if (skillRepository.count() == 0) { 
            Skill java = new Skill("Java", "Linguagem de programação orientada a objetos, amplamente utilizada para desenvolvimento de aplicações corporativas.", "https://i.pinimg.com/564x/86/ad/01/86ad01aac334ed269e9d33dab95a2217.jpg");
            Skill javascript = new Skill("JavaScript", "Linguagem de programação utilizada principalmente para desenvolvimento web, tanto no frontend quanto no backend.",  "https://upload.wikimedia.org/wikipedia/commons/6/6a/JavaScript-logo.png");
            Skill python = new Skill("Python", "Linguagem de programação poderosa e versátil, utilizada em diversas áreas como ciência de dados, automação e desenvolvimento web.", "https://i.pinimg.com/564x/82/a2/18/82a2188c985ce75402ae44fc43fe7e5e.jpg");
            Skill react = new Skill("React", "Biblioteca JavaScript para construir interfaces de usuário, especialmente para aplicações de página única.",  "https://i.pinimg.com/564x/0f/65/58/0f6558f88fb457e68f92c042df252892.jpg");
            Skill springBoot = new Skill("Spring Boot", "Framework Java que facilita o desenvolvimento de aplicações backend robustas e escaláveis.",  "https://i.pinimg.com/564x/fc/ae/74/fcae743dade228f0e06f30ca3cdd56ba.jpg");

            skillRepository.saveAll(Arrays.asList(java, javascript, python, react, springBoot));
        }
    }
}
