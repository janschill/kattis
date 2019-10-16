class Namable
  attr_accessor :name, :preferences

  def initialize(name, preferences*)
    @name = name
    @preferences = preferences
  end

  def add_to_prefenreces(preference)
    @preferences.push(preference)
  end
end