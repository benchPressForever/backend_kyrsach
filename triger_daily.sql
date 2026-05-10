CREATE OR REPLACE FUNCTION recalc_daily_stat_from_meals()
RETURNS TRIGGER AS $$
DECLARE
    total_calories DOUBLE PRECISION;
    total_protein DOUBLE PRECISION;
    total_fat DOUBLE PRECISION;
    total_carbs DOUBLE PRECISION;
BEGIN
    SELECT 
        COALESCE(SUM(calories), 0),
        COALESCE(SUM(protein), 0),
        COALESCE(SUM(fat), 0),
        COALESCE(SUM(carbs), 0)
    INTO 
        total_calories, total_protein, total_fat, total_carbs
    FROM meal
    WHERE daily_id = COALESCE(NEW.daily_id, OLD.daily_id);
    
    UPDATE daily_stat 
    SET 
        calories = total_calories,
        protein = total_protein,
        fat = total_fat,
        carbs = total_carbs
    WHERE id = COALESCE(NEW.daily_id, OLD.daily_id);
    
    RETURN COALESCE(NEW, OLD);
END;
$$ LANGUAGE plpgsql;

-- Триггер на INSERT
CREATE TRIGGER trigger_recalc_daily_insert
    AFTER INSERT ON meal
    FOR EACH ROW
    EXECUTE FUNCTION recalc_daily_stat_from_meals();

-- Триггер на UPDATE
CREATE TRIGGER trigger_recalc_daily_update
    AFTER UPDATE OF calories, protein, fat, carbs, daily_id ON meal
    FOR EACH ROW
    EXECUTE FUNCTION recalc_daily_stat_from_meals();

-- Триггер на DELETE
CREATE TRIGGER trigger_recalc_daily_delete
    AFTER DELETE ON meal
    FOR EACH ROW
    EXECUTE FUNCTION recalc_daily_stat_from_meals();