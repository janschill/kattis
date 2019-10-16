require_relative 'namable'

class Team < Namable
  attr_accessor :picks

  def add_to_picks(pick)
    @picks.push(pick)
  end
end