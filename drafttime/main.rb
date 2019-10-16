require_relative 'team'
require_relative 'player'

def gale_shapley(teams, players)
  team_with_free_spot = teams.clone
  stable_matching = []
  players_taken = []

  while (team_with_free_spot != {})
    team = team_with_free_spot.shift
    first_choice = team.preferences.shift

    if (!players_taken.include?(first_choice))
      players_taken.push(first_choice.name)
      stable_matching.push = [first_choice]
    elsif
      puts "elsif"
    else
      puts "else"
    end
  end
  puts stable_matching
end

class Main
  first_line = true;
  number_of_teams = 0
  number_of_draft_rounds = 0
  number_of_players = 0
  teams = []
  players = []

  STDIN.each_line do |line|
    if (first_line)
      nums = line.scan(/\d+/).map(&:to_i)
      number_of_teams = nums[0]
      number_of_draft_rounds = nums[1]
      number_of_players = nums[2]
      first_line = false
    else
      items = line.scan(/\w+/)
      first = items.shift
      if (number_of_teams > 0)
        teams.push(Team.new(first, items))
        number_of_teams -= 1
      else
        players.push(Player.new(first, items))
      end
    end
  end

  gale_shapley(teams, players)
end
