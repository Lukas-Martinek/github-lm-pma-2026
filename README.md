## Porovnání: Aktualizace zobrazení

**Klasické XML (imperativní přístup):**
Design a kód jsou oddělené. Při každé změně hodu se musí UI prvek ručně vyhledat v kódu (např. přes `findViewById`) a jeho hodnota natvrdo přepsat.

**Jetpack Compose (deklarativní přístup):**
Vše řídí stavová proměnná (`diceValue`). Jakmile se její hodnota změní, Compose to sám detekuje a automaticky překreslí jen tu část UI, která na ní závisí (tzv. rekompozice). Není nutné měnit prvky ručně.
