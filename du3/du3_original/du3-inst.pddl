(define (problem du3)
    (:domain du3)
    (:requirements :strips :typing)
    (:objects boat fregat caravel - ship
              island sea seamark dock city college bar river forest - place
              salior capitan admiral - sea_status
              sober funny drunk intox - alco_status
              walk swim - transport_status
              prison pirates girl fight bad_friend good_friend smuggler bride - flag_status
              married drugsdealer winner - final_status
              granule coin brick - gold_item
              coconut bear_skin - sale_item
              perl cocaine flower map - story_item
              wood alcohol - item 
    )
    (:init
        (neighbours island sea swim)
        (neighbours sea island swim)

        (neighbours sea seamark swim)
        (neighbours seamark sea swim)
        
        (neighbours sea dock swim)
        (neighbours dock sea swim)
        
        (neighbours seamark dock swim)
        (neighbours dock seamark swim)
        
        (neighbours dock river walk)
        (neighbours river dock walk)
        
        (neighbours bar dock walk)
        (neighbours dock bar walk)

        (neighbours city dock walk)
        (neighbours dock city walk)
        
        (neighbours river forest walk)
        (neighbours forest river walk)
        
        (neighbours city college walk)
        (neighbours college city walk)
        
        (one_drink alcohol sober funny)
        (two_drink alcohol funny drunk)
        (final_drink alcohol drunk intox)

        (can_study college prison)
        (up_salior_capitan salior capitan coin girl)
        (up_capitan_admiral capitan admiral pirates sober winner)

        (need_to_boat boat wood swim)
        (need_to_fregat fregat boat wood granule)
        (need_to_caravel caravel boat wood coin)

        (forest_action forest)
        (is_wood wood)
        (is_flower flower)
        (bear_fight bear_skin fight girl)
        (change_in_forest alcohol map bad_friend)

        (river_action river)
        (steal_boat boat prison swim)
        (is_gold_granule granule)
        (list_alco_status sober funny drunk intox)

        (dock_action dock)
        (is_gold_coin coin)
        (is_bear_skin bear_skin)
        (is_coconut coconut)
        (meet_smugglers brick bad_friend smuggler)

        (bar_action bar)
        (buy_alco alcohol granule)
        (buy_alco_for_all coin good_friend)
        (fight_in_bar sober fight)

        (city_action city)
        (farm_money granule coin good_friend)
        (invest_money coin brick good_friend)
        (steal_money coin prison)
        (pay_for_prison_free granule prison)
        (work_for_prison_free prison)

        (sea_action sea)
        (gold_list granule coin brick)
        (ship_list boat fregat caravel)
        (is_fight fight)
        (is_pirates pirates)
        (is_bad_friend bad_friend)
        (is_perl perl)
        (is_girl girl)

        (seamark_action seamark)
        (gift_for_girl brick perl flower girl bride)

        (island_action island)
        (find_cocaine map cocaine)

        (need_for_wending bride good_friend prison sober married winner)

        (nead_for_drugsdealer cocaine intox fregat smuggler brick drugsdealer winner)

        (in dock)

        (has_status walk)
        (has_status sober)
        (has_status salior)
    )
    (:goal (and
            (has_status winner)
        )
    )
)
