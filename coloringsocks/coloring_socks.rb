number_of_socks = 0
capacity = 0
threshold = 0
socks = []

STDIN.each_with_index do |line, index|
  if index == 0
    first_line = line.scan(/\d+/).map(&:to_i)
    number_of_socks = first_line[0]
    capacity = first_line[1]
    threshold = first_line[2]
  elsif index == 1
    socks = line.scan(/\d+/).map(&:to_i)
    socks.sort!
  end
end

total_count = 0
low_socks = 0

while true
  if low_socks >= number_of_socks
    break
  end
  max_socks = low_socks + capacity - 1
  if max_socks >= number_of_socks
    max_socks = number_of_socks - 1
  end
  while max_socks >= low_socks
    if socks[max_socks] - socks[low_socks] <= threshold
      total_count += 1
      low_socks = max_socks + 1
      break
    end
    max_socks -= 1
  end
end

puts total_count
