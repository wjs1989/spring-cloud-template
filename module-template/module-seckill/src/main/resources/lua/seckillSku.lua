local currentSku = -1
local deductSku = tonumber(ARGV[1])
local num = tonumber(redis.call('hget',KEYS[1],KEYS[2]))
if (num == nil or num < 0) then
    return currentSku
end
if(num >= deductSku) then
    currentSku = redis.call('hincrby',KEYS[1],KEYS[2],-1 * deductSku)
end
return currentSku