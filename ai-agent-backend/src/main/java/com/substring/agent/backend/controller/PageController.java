package com.substring.agent.backend.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // Kept in sync with the sample data in FlightTools / HotelTools so example prompts return results.
    private static final String ORIGIN = "Delhi";
    private static final String TRAVEL_DATE = "2026-08-21";

    private static final List<Destination> DESTINATIONS = List.of(
            new Destination("Goa", "Beaches, shacks & golden sunsets", "🏖️"),
            new Destination("Mumbai", "City lights, sea face & street food", "🌆"));

    private static final List<PromptGroup> PROMPT_GROUPS = List.of(
            new PromptGroup("Flights", "✈️", List.of(
                    "Find the cheapest flight from " + ORIGIN + " to Goa on " + TRAVEL_DATE,
                    "Compare all flights from " + ORIGIN + " to Mumbai on " + TRAVEL_DATE)),
            new PromptGroup("Hotels", "🏨", List.of(
                    "Show hotels in Goa under ₹2,500 per night",
                    "Which is the best-rated hotel in Mumbai under ₹5,000 per night?")),
            new PromptGroup("Weather", "⛅", List.of(
                    "What's the weather in Goa on " + TRAVEL_DATE + "?",
                    "Do I need an umbrella in Mumbai on " + TRAVEL_DATE + "?")),
            new PromptGroup("Budget trips", "💰", List.of(
                    "Plan a 3-night Goa trip from " + ORIGIN + " on " + TRAVEL_DATE + " for 1 person with ₹12,000",
                    "2 people, 2 nights in Mumbai from " + ORIGIN + " on " + TRAVEL_DATE
                            + " with a total budget of ₹25,000. What fits?")));

    private static final List<BudgetPreset> BUDGET_PRESETS = List.of(
            new BudgetPreset("Goa beach weekend", "🏖️", "Goa", 2, 2, 20000, "budget"),
            new BudgetPreset("Mumbai city break", "🌆", "Mumbai", 3, 1, 18000, "comfort"),
            new BudgetPreset("Luxury Goa escape", "🌴", "Goa", 4, 2, 50000, "luxury"));

    @GetMapping("/")
    public String chatPage(Model model) {
        model.addAttribute("appName", "Wanderly");
        model.addAttribute("tagline", "AI Travel Agent");
        model.addAttribute("origin", ORIGIN);
        model.addAttribute("travelDate", TRAVEL_DATE);
        model.addAttribute("travelDateLabel", LocalDate.parse(TRAVEL_DATE)
                .format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH)));
        model.addAttribute("destinations", DESTINATIONS);
        model.addAttribute("destinationNames", DESTINATIONS.stream().map(Destination::name).toList());
        model.addAttribute("promptGroups", PROMPT_GROUPS);
        model.addAttribute("budgetPresets", BUDGET_PRESETS);
        return "chat";
    }

    public record Destination(String name, String tagline, String emoji) {
    }

    public record PromptGroup(String label, String emoji, List<String> prompts) {
    }

    public record BudgetPreset(String title, String emoji, String destination,
                               int nights, int travellers, int budget, String style) {
    }
}
