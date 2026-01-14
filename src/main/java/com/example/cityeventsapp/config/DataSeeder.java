package com.example.cityeventsapp.config;

import com.example.cityeventsapp.model.Event;
import com.example.cityeventsapp.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final EventRepository eventRepository;


    @Override
    public void run(String... args) {
        if (eventRepository.count() > 0) {
            return;
        }

        List<Event> events = List.of(
                Event.builder()
                        .title("Концерт: акустичний вечір")
                        .eventTime(LocalDateTime.now().plusDays(2).withHour(19).withMinute(0))
                        .location("Міський палац культури")
                        .description("Живий акустичний концерт місцевих виконавців. Тривалість 90 хв.")
                        .imageUrl("https://images.unsplash.com/photo-1511379938547-c1f69419868d?auto=format&fit=crop&w=1200&q=60")
                        .build(),

                Event.builder()
                        .title("Лекція: основи Spring Boot")
                        .eventTime(LocalDateTime.now().plusDays(3).withHour(18).withMinute(30))
                        .location("Коворкінг “Hub”")
                        .description("Ввідна лекція про MVC, JPA та шаблонізацію. Для початківців.")
                        .imageUrl("https://images.unsplash.com/photo-1526374965328-7f61d4dc18c5?auto=format&fit=crop&w=1200&q=60")
                        .build(),

                Event.builder()
                        .title("Виставка сучасного мистецтва")
                        .eventTime(LocalDateTime.now().plusDays(5).withHour(11).withMinute(0))
                        .location("Арт-галерея")
                        .description("Експозиція робіт молодих художників. Вхід вільний.")
                        .imageUrl("https://images.unsplash.com/photo-1520697830682-bbb6e85e2b0d?auto=format&fit=crop&w=1200&q=60")
                        .build(),

                Event.builder()
                        .title("Кінопоказ: вечір короткометражок")
                        .eventTime(LocalDateTime.now().plusDays(6).withHour(20).withMinute(0))
                        .location("Кінотеатр “Central”")
                        .description("Добірка короткометражних фільмів. Після показу — обговорення.")
                        .imageUrl("https://images.unsplash.com/photo-1524985069026-dd778a71c7b4?auto=format&fit=crop&w=1200&q=60")
                        .build(),

                Event.builder()
                        .title("Ярмарок локальних брендів")
                        .eventTime(LocalDateTime.now().plusDays(7).withHour(12).withMinute(0))
                        .location("Центральна площа")
                        .description("Фудкорт, хендмейд, локальні виробники. Дружня атмосфера.")
                        .imageUrl("https://images.unsplash.com/photo-1521337706264-a414f153a5f5?auto=format&fit=crop&w=1200&q=60")
                        .build(),

                Event.builder()
                        .title("Майстер-клас: кава вдома")
                        .eventTime(LocalDateTime.now().plusDays(8).withHour(16).withMinute(0))
                        .location("Кав’ярня “Roast”")
                        .description("Як обрати зерно, помел і правильно заварювати. Практика включена.")
                        .imageUrl("https://images.unsplash.com/photo-1509042239860-f550ce710b93?auto=format&fit=crop&w=1200&q=60")
                        .build(),

                Event.builder()
                        .title("Спорт: ранкова пробіжка")
                        .eventTime(LocalDateTime.now().plusDays(9).withHour(8).withMinute(30))
                        .location("Міський парк")
                        .description("Легка пробіжка 5 км. Темп комфортний. Можна з друзями.")
                        .imageUrl("https://images.unsplash.com/photo-1549576490-b0b4831ef60a?auto=format&fit=crop&w=1200&q=60")
                        .build(),

                Event.builder()
                        .title("Зустріч: клуб настільних ігор")
                        .eventTime(LocalDateTime.now().plusDays(10).withHour(18).withMinute(0))
                        .location("Антикафе")
                        .description("Збираємось пограти в настолки. Є ігри для новачків і досвідчених.")
                        .imageUrl("https://images.unsplash.com/photo-1610890716171-6b1bb98ffd09?auto=format&fit=crop&w=1200&q=60")
                        .build(),

                Event.builder()
                        .title("Воркшоп: дизайн у Figma")
                        .eventTime(LocalDateTime.now().plusDays(11).withHour(17).withMinute(0))
                        .location("Освітній центр")
                        .description("Базові принципи UI, компоненти, сітки. Потрібен ноутбук.")
                        .imageUrl("https://images.unsplash.com/photo-1559028012-481c04fa702d?auto=format&fit=crop&w=1200&q=60")
                        .build(),

                Event.builder()
                        .title("Благодійний концерт")
                        .eventTime(LocalDateTime.now().plusDays(14).withHour(19).withMinute(30))
                        .location("Будинок органної музики")
                        .description("Збір коштів на важливу ініціативу. Квитки на місці.")
                        .imageUrl("https://images.unsplash.com/photo-1516450360452-9312f5e86fc7?auto=format&fit=crop&w=1200&q=60")
                        .build()
        );

        eventRepository.saveAll(events);
    }
}
