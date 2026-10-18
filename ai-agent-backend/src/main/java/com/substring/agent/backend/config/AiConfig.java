package com.substring.agent.backend.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.substring.agent.backend.tools.FlightTools;
import com.substring.agent.backend.tools.HotelTools;
import com.substring.agent.backend.tools.InventoryTools;
import com.substring.agent.backend.tools.OrderTools;
import com.substring.agent.backend.tools.WeatherTools;


@Configuration
public class AiConfig {

    private final OrderTools tools;
    private final InventoryTools inventoryTools;
    private final HotelTools hotelTools;
    private final WeatherTools weatherTools;
    private final FlightTools flightTools;

    public AiConfig(OrderTools tools,
                    InventoryTools inventoryTools,
                    HotelTools hotelTools,
                    WeatherTools weatherTools,
                    FlightTools flightTools) {

        this.tools = tools;
        this.inventoryTools = inventoryTools;
        this.hotelTools = hotelTools;
        this.weatherTools = weatherTools;
        this.flightTools = flightTools;
    }
    @Bean
    public ChatMemory chatMemory(){
        return MessageWindowChatMemory
        .builder()
        .maxMessages(10)
        .build();
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {

        return builder
                .defaultSystem("""
                        You are a helpful travel planning agent and customer support agent for an online store.

                        Use the available tools to:
                        - Check order status
                        - Check inventory information
                        - Search flights
                        - Search hotels
                        - Get weather forecasts

                        When given a budget, suggest options that fit within it and explain your reasoning.
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory()).build())        
                .defaultTools(
                        tools,
                        inventoryTools,
                        flightTools,
                        hotelTools,
                        weatherTools
                )
                .build();
    }
}